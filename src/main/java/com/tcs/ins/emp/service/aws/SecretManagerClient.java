package com.tcs.ins.emp.service.aws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Configuration;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;

@Configuration
public class SecretManagerClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecretManagerClient.class);

	public AWSSecretsManager awsSecretManager() {
		LOGGER.info("Value of AWS Secret Manager {}"
				+ AWSSecretsManagerClientBuilder.standard().withRegion(Regions.US_EAST_1).build());
		return AWSSecretsManagerClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	}
}
