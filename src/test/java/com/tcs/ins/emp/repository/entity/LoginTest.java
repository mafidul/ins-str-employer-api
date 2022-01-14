package com.tcs.ins.emp.repository.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({ MockitoExtension.class })
public class LoginTest {

	private static final Long REQUEST_PARAM_ID = 1L;
	private static final Long REQUEST_PARAM_CONTACT_NO = 9856352563L;

	private static final String REQUEST_PARAM_FAX_NO = "033 6985632563";
	private static final String REQUEST_PARAM_EMPLOYER_PASSWORD = "Admin@123";
	private static final String REQUEST_PARAM_COMPANY_NAME = "TCS";
	private static final String REQUEST_PARAM_EMPLOYER_EMAIL = "Admin@gmail.com";
	private static final String REQUEST_PARAM_EMPLOYER_LOCATION = "Kolkata";
	private static final String REQUEST_PARAM_EMPLOYER_TYPE = "Kolkata";
	private static final String REQUEST_PARAM_EMPLOYER_ADDRESS = "Admin@gmail.com";

	@Test
	void idTest() {
		Login login = new Login();
		login.setId(REQUEST_PARAM_ID);
		assertThat(login.getId()).isEqualTo(REQUEST_PARAM_ID);
	}

	@Test
	void contactNoTest() {
		Login login = new Login();
		login.setContactNo(REQUEST_PARAM_CONTACT_NO);
		assertThat(login.getContactNo()).isEqualTo(REQUEST_PARAM_CONTACT_NO);
	}

	@Test
	void passwordTest() {
		Login login = new Login();
		login.setPassword(REQUEST_PARAM_EMPLOYER_PASSWORD);
		assertThat(login.getPassword()).isEqualTo(REQUEST_PARAM_EMPLOYER_PASSWORD);
	}

	@Test
	void confirmPasswordTest() {
		Login login = new Login();
		login.setConfirmPassword(REQUEST_PARAM_EMPLOYER_PASSWORD);
		assertThat(login.getConfirmPassword()).isEqualTo(REQUEST_PARAM_EMPLOYER_PASSWORD);
	}

	@Test
	void companyNameTest() {
		Login login = new Login();
		login.setCompanyName(REQUEST_PARAM_COMPANY_NAME);
		assertThat(login.getCompanyName()).isEqualTo(REQUEST_PARAM_COMPANY_NAME);
	}

	@Test
	void faxNoTest() {
		Login login = new Login();
		login.setFaxNo(REQUEST_PARAM_FAX_NO);
		assertThat(login.getFaxNo()).isEqualTo(REQUEST_PARAM_FAX_NO);
	}

	@Test
	void emailTest() {
		Login login = new Login();
		login.setEmail(REQUEST_PARAM_EMPLOYER_EMAIL);
		assertThat(login.getEmail()).isEqualTo(REQUEST_PARAM_EMPLOYER_EMAIL);
	}

	@Test
	void locationTest() {
		Login login = new Login();
		login.setLocation(REQUEST_PARAM_EMPLOYER_LOCATION);
		assertThat(login.getLocation()).isEqualTo(REQUEST_PARAM_EMPLOYER_LOCATION);
	}

	@Test
	void companyTypeTest() {
		Login login = new Login();
		login.setCompanyType(REQUEST_PARAM_EMPLOYER_TYPE);
		assertThat(login.getCompanyType()).isEqualTo(REQUEST_PARAM_EMPLOYER_TYPE);
	}

	@Test
	void addressTest() {
		Login login = new Login();
		login.setAddress(REQUEST_PARAM_EMPLOYER_ADDRESS);
		assertThat(login.getAddress()).isEqualTo(REQUEST_PARAM_EMPLOYER_ADDRESS);
	}
}
