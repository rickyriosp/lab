import { Construct } from 'constructs';
import * as cdk from 'aws-cdk-lib';
import * as lambda from 'aws-cdk-lib/aws-lambda-nodejs';
import { Runtime } from 'aws-cdk-lib/aws-lambda';
import * as s3 from 'aws-cdk-lib/aws-s3';
import * as iam from 'aws-cdk-lib/aws-iam';
import * as apigwv2 from 'aws-cdk-lib/aws-apigatewayv2';
import { HttpLambdaIntegration } from 'aws-cdk-lib/aws-apigatewayv2-integrations';
import path from 'path';

interface DocumentManagementApiProps {
    documentBucket: s3.IBucket
}

export class DocumentManagementApi extends Construct {

    public readonly httpApi: apigwv2.HttpApi;

    constructor(scope: Construct, id: string, props: DocumentManagementApiProps) {
        super(scope,id);

        const getDocumentsFunction = new lambda.NodejsFunction(this, 'GetDocumentsFunction', {
            runtime: Runtime.NODEJS_20_X,
            entry: path.join(__dirname, '..', 'api', 'getDocuments', 'index.ts'),
            handler: 'getDocuments',
            bundling: {
                externalModules: [
                    '@aws-sdk/*'
                ]
            },
            environment: {
                DOCUMENTS_BUCKET_NAME: props.documentBucket.bucketName
            }
        });
        
        const bucketPermissions = new iam.PolicyStatement();
        bucketPermissions.addResources(`${props.documentBucket.bucketArn}/*`);
        bucketPermissions.addActions('s3:GetObject', 's3:PutObject');
        getDocumentsFunction.addToRolePolicy(bucketPermissions);

        const bucketContainerPermissions = new iam.PolicyStatement();
        bucketContainerPermissions.addResources(`${props.documentBucket.bucketArn}`);
        bucketContainerPermissions.addActions('s3:ListBucket');
        getDocumentsFunction.addToRolePolicy(bucketContainerPermissions);

        this.httpApi = new apigwv2.HttpApi(this, 'HttpAPI', {
            apiName: 'document-management-api',
            createDefaultStage: true,
            corsPreflight: {
                allowMethods: [ apigwv2.CorsHttpMethod.GET ],
                allowOrigins: ['*'],
                maxAge: cdk.Duration.days(10)
            }
        });

        const integration = new HttpLambdaIntegration('DocumentsApiIntegration', getDocumentsFunction);
        
        this.httpApi.addRoutes({
            path: '/getDocuments',
            methods: [ apigwv2.HttpMethod.GET ],
            integration: integration
        });

        new cdk.CfnOutput(this, 'APIEndopint', {
            value: this.httpApi.url!,
            exportName: 'APIEndpoint'
        });
    }
};