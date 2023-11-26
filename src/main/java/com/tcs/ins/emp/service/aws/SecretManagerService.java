package com.tcs.ins.emp.service.aws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;

@Configuration
public class SecretManagerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecretManagerClient.class);

	@Value("${secret-name}")
	protected String dbSecretName;

	public String getDbSecretValue() {
		return getSecretValue(dbSecretName);
	}

	private String getSecretValue(String secretName) {

		LOGGER.info("Inside getSecretValue Method {}", secretName);
		GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest();
		GetSecretValueResult getSecretValueResult = null;
		try {
			getSecretValueResult.setSecretString(secretName);
			String secret = getSecretValueResult.getSecretString();
			return secret;
		} catch (Exception ex) {
		}
		return null;
	}
}
