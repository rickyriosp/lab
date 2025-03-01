import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import * as ec2 from 'aws-cdk-lib/aws-ec2';

interface NetworkingProps {
    maxAzs: number;
}

export class Networking extends Construct {

    public readonly vpc: ec2.IVpc;

    constructor(scope: Construct, id: string, props: NetworkingProps) {
        super(scope, id);

        //const vpc = new ec2.Vpc(this, 'VPC'); //basic vpc
        this.vpc = new ec2.Vpc(this, 'AppVPC', {
           ipAddresses: ec2.IpAddresses.cidr('10.0.0.0/16'),
           maxAzs: props.maxAzs,
           subnetConfiguration: [
            {
                subnetType: ec2.SubnetType.PUBLIC,
                name: 'Public',
                cidrMask: 24,
            },
            {
                subnetType: ec2.SubnetType.PRIVATE_WITH_EGRESS,
                name: 'Private',
                cidrMask: 24,
            }
           ],
        });
    }
}