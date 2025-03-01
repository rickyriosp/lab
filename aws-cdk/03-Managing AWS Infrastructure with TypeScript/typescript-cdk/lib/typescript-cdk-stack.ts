import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import * as s3 from 'aws-cdk-lib/aws-s3';
import * as s3deploy from 'aws-cdk-lib/aws-s3-deployment'
import * as iam from 'aws-cdk-lib/aws-iam';
import * as dynamodb from 'aws-cdk-lib/aws-dynamodb';
import * as cf from 'aws-cdk-lib/aws-cloudfront';
import * as origins from 'aws-cdk-lib/aws-cloudfront-origins';
import path = require('path');

export class TypescriptCdkStack extends cdk.Stack {

  private readonly projectName: string = 'MyCdkGoals';
  private readonly tableName: string = 'CdkGoals';
  private readonly websiteIndexDocument: string = 'index.html';

  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    /* Dynamo Objects */
    //#region
    /* Create DynamoDB Goals Table */
    const goalsTable = new dynamodb.TableV2(this, 'TGoals', {
      tableName: `${this.projectName}-${this.tableName}`,
      partitionKey: { name: 'userId', type: dynamodb.AttributeType.STRING },
      sortKey: { name: 'goalId', type: dynamodb.AttributeType.STRING },
      billing: dynamodb.Billing.provisioned({
        readCapacity: dynamodb.Capacity.fixed(1),
        writeCapacity: dynamodb.Capacity.autoscaled({ maxCapacity: 1 })
      }),
      removalPolicy: cdk.RemovalPolicy.DESTROY,
    });

    /* Create DynamoDB Role/Policy */
    const dynamoDbRole = new iam.Role(this, 'DynamoDbRole', {
      assumedBy: new iam.ServicePrincipal('lambda.amazonaws.com')
    });

    const goalsPolicy = new iam.Policy(this, 'GoalsPolicy', {
      policyName: 'GoalsPolicy',
      roles: [ dynamoDbRole ],
      statements: [
        new iam.PolicyStatement({
          effect: iam.Effect.ALLOW,
          actions: [ 'dynamodb:*' ],
          resources: [ goalsTable.tableArn ]
        })
      ]
    });
    //#endregion
 

    /* S3 Objects */
    //ToDo - grant access to cloudfront user and uncomment block all
    //#region
    /* Assets Source Bucket weill be used as a codebuild source for the react code */
    const sourceAssetBucket = new s3.Bucket(this, 'SourceAssetBucket', {
      bucketName: `aws-fullstack-template-source-assets-${getRandomInt(10000000)}`,
      blockPublicAccess: s3.BlockPublicAccess.BLOCK_ALL,
      removalPolicy: cdk.RemovalPolicy.DESTROY,
      versioned: true
    });

    /* Website Bucket is the target bucket for the react application */
    const websiteBucket = new s3.Bucket(this, 'WebsiteBucket', {
      bucketName: `aws-fullstack-template-website-${getRandomInt(10000000)}`,
      removalPolicy: cdk.RemovalPolicy.DESTROY,
      websiteIndexDocument: this.websiteIndexDocument,
      websiteErrorDocument: this.websiteIndexDocument,
      publicReadAccess: true,
      blockPublicAccess: {
        blockPublicPolicy: false,
        blockPublicAcls: false,
        ignorePublicAcls: false,
        restrictPublicBuckets: false,
      },
    });

    /* Pipeline Artifacts Bucket is used by CodePipeline during Builds */
    const pipelineArtifactsBucket = new s3.Bucket(this, 'PipelineArtifactsBucket', {
      bucketName: `aws-fullstack-template-codepipeline-artifacts-${getRandomInt(1000000)}`,
      blockPublicAccess: s3.BlockPublicAccess.BLOCK_ALL,
      removalPolicy: cdk.RemovalPolicy.DESTROY
    });

    /* S3 Website Deployment */
    const s3WebsiteDeployment = new s3deploy.BucketDeployment(this, 'S3WebsiteDeploy', {
      sources: [ s3deploy.Source.asset(path.join(__dirname, '..', '..', 'assets', 'archive')) ],
      destinationBucket: sourceAssetBucket
    });

    /* Set Website Bucket Allow Policy */
    websiteBucket.addToResourcePolicy(
      new iam.PolicyStatement({        
        actions: [ 's3:Get*'],
        principals: [ new iam.AnyPrincipal ],
        resources: [
          `${websiteBucket.bucketArn}/*`
        ],
      })
    );
    //#endregion

    /* Cloudfront CDN Distribution */
    //#region
    const assetsCdn = new cf.Distribution(this, 'AssetsCdn', {
      defaultRootObject: this.websiteIndexDocument,
      comment: `CDN for ${websiteBucket}`,
      defaultBehavior: {
        origin: new origins.S3StaticWebsiteOrigin(websiteBucket)
      },
    });
    //#endregion

    /* Lambda Objects */
    //#region
  }
}

const getRandomInt = (max: number) => {
  return Math.floor(Math.random() * Math.floor(max));
}
