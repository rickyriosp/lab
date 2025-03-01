import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import * as apigwv2 from 'aws-cdk-lib/aws-apigatewayv2';
import * as apigwv2integration from 'aws-cdk-lib/aws-apigatewayv2-integrations';
import * as lambda from 'aws-cdk-lib/aws-lambda';
import * as iam from 'aws-cdk-lib/aws-iam';
import * as dynamodb from 'aws-cdk-lib/aws-dynamodb';
import path = require('path');
import * as lambdaNodejs from 'aws-cdk-lib/aws-lambda-nodejs';

export class ServerlessStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    //define dynamodb table
    const table = new dynamodb.TableV2(this, 'Table', {
      tableName: 'DynamoTable',
      partitionKey: { name: 'pk', type: dynamodb.AttributeType.STRING },
      removalPolicy: cdk.RemovalPolicy.DESTROY
    });

    //grant lambda access to read dynamodb
    const principal = new iam.ServicePrincipal("lambda.amazonaws.com");
    const lambdaRole = new iam.Role(this, "lambdaRole", {
      assumedBy: principal
    });
    table.grantReadData(lambdaRole);

    //define lambda function and reference function file
    const lambdaFn = new lambdaNodejs.NodejsFunction(this, 'LambdaFn', {
      functionName: 'LambdaFn',
      runtime: lambda.Runtime.NODEJS_22_X,
      handler: 'handler',
      entry: path.join(__dirname, '..', 'src','index.ts'), // esbuild is need to build from local, otherwise it's Docker
      environment: {
        DYNAMODB: table.tableName
      },
      tracing: lambda.Tracing.ACTIVE,
      bundling: {
        minify: true, // minify code, defaults to false
        target: 'es2020', // target environment for the generated JavaScript code
        externalModules: [
          // wihtout including aws-sdk below lambda was not working
          '@aws-sdk/*', // Use the AWS SDK for JS v3 available in the Lambda runtime
        ],
      },
    });
    // const lambdaFn = new lambda.Function(this, 'LambdaFn', {
    //   functionName: 'LambdaFn',
    //   runtime: lambda.Runtime.NODEJS_22_X,
    //   handler: 'index.handler',
    //   code: lambda.Code.fromAsset(path.join(__dirname, '..', 'src')),
    //   //role: lambdaRole, //use this if created role above
    //   environment: {
    //     DYNAMODB: table.tableName
    //   }
    //   tracing: lambda.Tracing.ACTIVE
    // });

    //grant lambda access to read dynamodb
    table.grantReadData(lambdaFn); // this works as well

    //define apigateway
    const httpApi = new apigwv2.HttpApi(this, 'HttpApi', {
      apiName: 'HttpApi',
    });

    //define lambda integration
    const lambdaIntegration = new apigwv2integration.HttpLambdaIntegration('LambdaIntegration', lambdaFn);

    //define endpoint and associate it with lambda backend
    const routes = httpApi.addRoutes({
      path: '/scan',
      methods: [ apigwv2.HttpMethod.GET ],
      integration: lambdaIntegration,
    });

    new cdk.CfnOutput(this, 'ApiURL', { value: httpApi.apiEndpoint + routes[0].path });
  }
}
