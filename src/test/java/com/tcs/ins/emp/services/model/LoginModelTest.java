package com.tcs.ins.emp.services.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tcs.ins.emp.service.model.LoginModel;

@ExtendWith({ MockitoExtension.class })
public class LoginModelTest {

	private static final long LOGIN_ID = 1L;
	private static final long CONTACT_NO = 1256333L;

	private static final String COMPANY_TYPE = "MNC";
	private static final String COMPANY_NAME = "TCS";
	private static final String ADDRESS_DETAIL = "Kolkata";
	private static final String LOCATION_DETAIL = "Kolkata";
	private static final String EAMIL_ID = "email@gmail.com";
	private static final String FAX_NO = "033 23123";
	private static final String LOGIN_PASSWORD = "Admin@123";

	@Test
	void idTest() {
		LoginModel loginModel = new LoginModel();
		loginModel.setId(LOGIN_ID);
		assertThat(loginModel.getId()).isEqualTo(LOGIN_ID);
	}

	@Test
	void contactNoTest() {
		LoginModel loginModel = new LoginModel();
		loginModel.setContactNo(CONTACT_NO);
		assertThat(loginModel.getContactNo()).isEqualTo(CONTACT_NO);
	}

	@Test
	void passwordTest() {
		LoginModel loginModel = new LoginModel();
		loginModel.setPassword(LOGIN_PASSWORD);
		assertThat(loginModel.getPassword()).isEqualTo(LOGIN_PASSWORD);
	}

	@Test
	void confirmPasswordTest() {
		LoginModel loginModel = new LoginModel();
		loginModel.setConfirmPassword(LOGIN_PASSWORD);
		assertThat(loginModel.getConfirmPassword()).isEqualTo(LOGIN_PASSWORD);
	}

	@Test
	void faxNoTest() {
		LoginModel loginModel = new LoginModel();
		loginModel.setFaxNo(FAX_NO);
		assertThat(loginModel.getFaxNo()).isEqualTo(FAX_NO);
	}

	@Test
	void emailTest() {
		LoginModel loginModel = new LoginModel();
		loginModel.setEmail(EAMIL_ID);
		assertThat(loginModel.getEmail()).isEqualTo(EAMIL_ID);
	}

	@Test
	void addressTest() {
		LoginModel loginModel = new LoginModel();
		loginModel.setAddress(ADDRESS_DETAIL);
		assertThat(loginModel.getAddress()).isEqualTo(ADDRESS_DETAIL);
	}

	@Test
	void locationTest() {
		LoginModel loginModel = new LoginModel();
		loginModel.setLocation(LOCATION_DETAIL);
		assertThat(loginModel.getLocation()).isEqualTo(LOCATION_DETAIL);
	}

	@Test
	void companyNameTest() {
		LoginModel loginModel = new LoginModel();
		loginModel.setCompanyName(COMPANY_NAME);
		assertThat(loginModel.getCompanyName()).isEqualTo(COMPANY_NAME);
	}

	@Test
	void companyTypeTest() {
		LoginModel loginModel = new LoginModel();
		loginModel.setCompanyType(COMPANY_TYPE);
		assertThat(loginModel.getCompanyType()).isEqualTo(COMPANY_TYPE);
	}

	@Test
	void toStringTest() {
		LoginModel loginModel = new LoginModel();
		loginModel.setId(1L);
		assertThat(loginModel.toString()).isNotEmpty();
	}
}
