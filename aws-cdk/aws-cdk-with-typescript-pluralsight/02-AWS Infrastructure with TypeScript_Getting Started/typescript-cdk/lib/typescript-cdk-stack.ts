import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
// import * as sqs from 'aws-cdk-lib/aws-sqs';
import * as s3 from 'aws-cdk-lib/aws-s3';
import * as s3deploy from 'aws-cdk-lib/aws-s3-deployment';
import { Networking } from './networking';
import { DocumentManagementApi } from './api';
import { DocumentManagementWebserver } from './webserver';
import path from 'path';


export class TypescriptCdkStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    // The code that defines your stack goes here

    // example resource
    // const queue = new sqs.Queue(this, 'TypescriptCdkQueue', {
    //   visibilityTimeout: cdk.Duration.seconds(300)
    // });

    const bucket = new s3.Bucket(this, 'DocumentsBucket', {
      encryption: s3.BucketEncryption.S3_MANAGED,
    });

    new s3deploy.BucketDeployment(this, 'DocumentsDeployment', {
      sources: [
        s3deploy.Source.asset(path.join(__dirname, '..', 'documents'))
      ],
      destinationBucket: bucket,
      memoryLimit: 512
    })

    new cdk.CfnOutput(this, 'DocumentsBucketNameExport', {
      value: bucket.bucketName,
      exportName: 'DocumentsBucketName'
    });

    const networkingStack = new Networking(this, 'NetworkingConstruct', {
      maxAzs: 2
    });

    cdk.Tags.of(networkingStack).add('Module', 'Networking');

    const api = new DocumentManagementApi(this, 'DocumentManagementFunction', {
      documentBucket: bucket
    });

    cdk.Tags.of(api).add('Module', 'API');

    const webserver = new DocumentManagementWebserver(this, 'DocumentManagenemtWebserver', {
      vpc: networkingStack.vpc,
      api: api.httpApi
    });

    cdk.Tags.of(webserver).add('Module', 'Webserver');
  }
}
