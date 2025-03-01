import { Construct } from 'constructs';
import * as cdk from 'aws-cdk-lib'; 
import * as ec2 from 'aws-cdk-lib/aws-ec2';

interface VpcSubnetsProperties {};

export class VpcSubnets extends Construct {
    constructor(stack: Construct, id: string, props?: VpcSubnetsProperties) {
        super(stack, id);

        const vpc = new ec2.Vpc(this, 'VPC', {
            // ipAddresses: ec2.IpAddresses.cidr('192.168.0.0/16'),
            maxAzs: 6,
            subnetConfiguration: [
                {
                    cidrMask: 24,
                    name: 'Public',
                    subnetType: ec2.SubnetType.PUBLIC
                },
                {
                    cidrMask: 24,
                    name: 'PrivateEgress',
                    subnetType: ec2.SubnetType.PRIVATE_WITH_EGRESS
                },
                {
                    cidrMask: 24,
                    name: 'Private',
                    subnetType: ec2.SubnetType.PRIVATE_ISOLATED
                }
            ]
        });

        // const publicSubnet = new ec2.PublicSubnet(this, 'PublicSubnet', {
        //     availabilityZone: vpc.availabilityZones[0],
        //     cidrBlock: vpc.vpcCidrBlock,
        //     vpcId: vpc.vpcId,
        //     mapPublicIpOnLaunch: true
        // });

        // const privateSubnet = new ec2.PrivateSubnet(this, 'PrivateSubnet', {
        //     availabilityZone: vpc.availabilityZones[0],
        //     cidrBlock: vpc.vpcCidrBlock,
        //     vpcId: vpc.vpcId,
        //     mapPublicIpOnLaunch: false
        // });
    }
}