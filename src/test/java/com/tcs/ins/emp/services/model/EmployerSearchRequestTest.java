package com.tcs.ins.emp.services.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tcs.ins.emp.service.model.EmployerSearchRequest;

@ExtendWith({ MockitoExtension.class })
public class EmployerSearchRequestTest {

	private static final String REQUEST_PARAM_ID = "id";
	private static final String REQUEST_PARAM_CONTACT_NO = "contactNo";
	private static final String REQUEST_PARAM_EMAIL = "email";
	private static final String REQUEST_PARAM_COMPANY_NAME = "companyName";

	@Test
	void idShouldBeInvalid() {
		EmployerSearchRequest EmployerSearchRequest = new EmployerSearchRequest(new HashMap<String, String>());
		assertFalse(EmployerSearchRequest.idFilteringRequired());
	}

	@Test
	void idShouldNotBeInvalid() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "1");
		EmployerSearchRequest employerSearchRequest = new EmployerSearchRequest(map);
		assertTrue(employerSearchRequest.idFilteringRequired());
	}

	@Test
	void contactNoShouldBeInvalid() {
		EmployerSearchRequest EmployerSearchRequest = new EmployerSearchRequest(new HashMap<String, String>());
		assertFalse(EmployerSearchRequest.contactNoFilteringRequired());
	}

	@Test
	void contactNoShouldNotBeInvalid() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("contactNo", "9856325632");
		EmployerSearchRequest employerSearchRequest = new EmployerSearchRequest(map);
		assertTrue(employerSearchRequest.contactNoFilteringRequired());
	}

	@Test
	void companyNameShouldBeInvalid() {
		EmployerSearchRequest EmployerSearchRequest = new EmployerSearchRequest(new HashMap<String, String>());
		assertFalse(EmployerSearchRequest.companyNameFilteringRequired());
	}

	@Test
	void emailShouldBeInvalid() {
		EmployerSearchRequest EmployerSearchRequest = new EmployerSearchRequest(new HashMap<String, String>());
		assertFalse(EmployerSearchRequest.emailFilteringRequired());
	}

	@Test
	void idTest() {
		EmployerSearchRequest EmployerSearchRequest = new EmployerSearchRequest(validMap());
		assertThat(EmployerSearchRequest.getId()).isEqualTo(1L);
	}

	@Test
	void emailTest() {
		EmployerSearchRequest EmployerSearchRequest = new EmployerSearchRequest(validMap());
		assertThat(EmployerSearchRequest.getCompanyName()).isEqualTo("TCS");
	}

	@Test
	void companyNameTest() {
		EmployerSearchRequest EmployerSearchRequest = new EmployerSearchRequest(validMap());
		assertThat(EmployerSearchRequest.getContactNo()).isEqualTo(9856325632L);
	}

	@Test
	void contactNoTest() {
		EmployerSearchRequest EmployerSearchRequest = new EmployerSearchRequest(validMap());
		assertThat(EmployerSearchRequest.getEmail()).isEqualTo("abc@gmail.com");
	}

	@Test
	void idShouldBeNullTest() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "1");
		map.put("contactNo", "1");
		EmployerSearchRequest employerSearchRequest = new EmployerSearchRequest(map);
		assertThat(employerSearchRequest.getId()).isEqualTo(1L);
	}

	private Map<String, String> validMap() {
		Map<String, String> map = new HashMap<>();
		map.put(REQUEST_PARAM_ID, "1");
		map.put(REQUEST_PARAM_CONTACT_NO, "9856325632");
		map.put(REQUEST_PARAM_COMPANY_NAME, "TCS");
		map.put(REQUEST_PARAM_EMAIL, "abc@gmail.com");
		return map;
	}
}
