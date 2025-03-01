import { Construct } from 'constructs';
import * as cdk from 'aws-cdk-lib';
import * as path from 'path';
import { DockerImageAsset, Platform } from 'aws-cdk-lib/aws-ecr-assets';
import * as ec2 from 'aws-cdk-lib/aws-ec2';
import * as ecsp from 'aws-cdk-lib/aws-ecs-patterns';
import * as ecs from 'aws-cdk-lib/aws-ecs';
import * as ecr from 'aws-cdk-lib/aws-ecr';
import * as apigw2 from 'aws-cdk-lib/aws-apigatewayv2';

interface DocumentMansgementWebserverProps {
    vpc: ec2.IVpc;
    api: apigw2.HttpApi;
}

export class DocumentManagementWebserver extends Construct {
    constructor (scope: Construct, id: string, props: DocumentMansgementWebserverProps) {
        super(scope, id);

        // const webserverDocker = new DockerImageAsset(this, 'WebserverDockerAsset', {
        //     directory: path.join(__dirname, '..', 'containers', 'webserver'),
        //     platform: Platform.LINUX_AMD64,
        //     outputs: ['type=image,push=true,oci-mediatypes=false'],
        //     cacheDisabled: true,
        // });
        
        const repository = new ecr.Repository(this, 'WebserverDockerRepo', {
            imageScanOnPush: true,
        });

        const fargateService = new ecsp.ApplicationLoadBalancedFargateService(this, 'WebserverService', {
            vpc: props.vpc,
            taskImageOptions: {
                // image: ecs.ContainerImage.fromDockerImageAsset(webserverDocker),
                image: ecs.ContainerImage.fromRegistry("amazon/amazon-ecs-sample"),
                environment: {
                    SERVER_PORT: "8080",
                    API_BASE: props.api.url!
                },
                containerPort: 8080
            }
        });

        new cdk.CfnOutput(this, 'WebserverHost', {
            exportName: 'WebserverHost',
            value: fargateService.loadBalancer.loadBalancerDnsName
        });
    }
}