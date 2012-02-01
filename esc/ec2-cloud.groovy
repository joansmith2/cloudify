
cloud {
	name = "ec2"
	configuration {
		className "org.cloudifysource.esc.driver.provisioning.jclouds.DefaultProvisioningDriver"
		managementMachineTemplate "SMALL_LINUX_32"
		connectToPrivateIp true
	}

	provider {
		provider "aws-ec2"
		localDirectory "tools/cli/plugins/esc/ec2/upload"
		remoteDirectory "/home/ec2-user/gs-files"
		cloudifyUrl "http://repository.openspaces.org/repository/org/cloudifysource/2.0.1/gigaspaces-cloudify-2.0.1-m1-b1190-5.zip" 
		machineNamePrefix "gs_esm_gsa_"
		
		dedicatedManagementMachines true
		managementOnlyFiles ([])
		

		sshLoggingLevel "WARNING"
		managementGroup "unit_test_management_machine"
		numberOfManagementMachines 1
		zones (["agent"])
		reservedMemoryCapacityPerMachineInMB 1024
		
	}
	user {
		user "ENTER_USER"
		apiKey "ENTER_API_KEY"
		keyFile "cloud-demo.pem"
	}
	templates ([
				SMALL_LINUX_32 : template{
					imageId "us-east-1/ami-76f0061f"
					machineMemoryMB 1600
					hardwareId "m1.small"
					locationId "us-east-1"
					options ([
						"securityGroups" : ["default"] as String[],
						"keyPair" : "cloud-demo"
					])
				}
			])
}

