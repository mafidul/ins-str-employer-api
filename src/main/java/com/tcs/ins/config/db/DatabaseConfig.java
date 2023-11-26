//package com.tcs.ins.config.db;
//
//import java.text.ParseException;
//import java.util.Map;
//
//import javax.sql.DataSource;
//
//import org.hibernate.service.spi.ServiceException;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectReader;
//import com.tcs.ins.emp.service.aws.SecretManagerService;
//
//@Configuration
//public class DatabaseConfig {
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);
//
//	ObjectReader reader = new ObjectMapper().readerFor(Map.class);
//
//	@Value("${spring.datasource.username}")
//	String dbUserName;
//	@Value("${spring.datasource.password}")
//	String password;
//
//	@Autowired
//	private SecretManagerService secretManagerService;
//
//	@Bean
//	public DataSource dataSource()
//			throws ServiceException, ParseException, JsonMappingException, JsonProcessingException {
//		Map<String, String> map = getDataConfigProd();
//		if (map != null) {
//			return DataSourceBuilder.create().username(dbUserName).password(password).build();
//		}
//		return null;
//	}
//
//	private Map<String, String> getDataConfigProd() throws JsonMappingException, JsonProcessingException {
//		String secretValue = secretManagerService.toString();
//		Map<String, String> map = reader.readValue("44");
//		return null;
//	}
//}
