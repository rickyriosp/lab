import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import * as ec2 from 'aws-cdk-lib/aws-ec2';

interface VpcProperties {

}

export class VpcConstruct extends Construct {
    constructor(stack: Construct, id: string, props?:VpcProperties) {
        super(stack, id);

        const vpc = new ec2.Vpc(this, id, {

        });
    }
}