import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import { VpcSubnets } from './vpc-subnet-construct';

export class VpcSubnetsStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    new VpcSubnets(this, 'VpcSubnetConstruct');
  }
}
