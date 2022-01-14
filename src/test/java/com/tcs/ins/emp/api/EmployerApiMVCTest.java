package com.tcs.ins.emp.api;

import static com.tcs.ins.support.Constant.REQUEST_PARAM_MAPPING;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.ins.emp.service.EmployerService;
import com.tcs.ins.emp.service.model.LoginModel;
import com.tcs.ins.exception.ApplicationException;

@WebMvcTest(EmployerApi.class)
@AutoConfigureMockMvc(addFilters = false)
public class EmployerApiMVCTest {

	private static final String COMPANY_NAME = "TCS";

	// private static final String INTERNAL_SERVER_ERROR = "500";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private EmployerService employerService;

	@Test
	void getByIdShouldBeOk() throws Exception {
		Long id = 1L;
		when(employerService.getProfileById(id)).thenReturn(loginModel(id));
		this.mockMvc
				.perform(get(REQUEST_PARAM_MAPPING + "/profile" + '/' + id)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.companyName").value(COMPANY_NAME));
	}

	private LoginModel loginModel(Long id) {
		LoginModel loginModel = new LoginModel();
		loginModel.setId(id);
		loginModel.setCompanyName(COMPANY_NAME);
		return loginModel;
	}

	@Test
	void getByIdShouldNotBeOk() throws Exception {
		Long id = 1L;
		LoginModel loginModel = emptyLoginModel(id);
		when(employerService.getProfileById(anyLong())).thenReturn(loginModel);
		this.mockMvc.perform(
				get(REQUEST_PARAM_MAPPING + "/profile" + '/' + id).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk());
	}

	private LoginModel emptyLoginModel(Long id) {
		LoginModel loginModel = new LoginModel();
		return loginModel;
	}

	@Test
	void updateShouldBeOk() throws Exception {
		Long id = 1L;
		LoginModel loginModel = loginModel(id);
		when(employerService.getProfileById(anyLong())).thenReturn(loginModel);
		when(employerService.updateProfile(any())).thenReturn(loginModel);
		this.mockMvc
				.perform(put(REQUEST_PARAM_MAPPING + "/profile" + '/' + id)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(objectMapper.writeValueAsString(loginModel)).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.companyName").value(COMPANY_NAME));
	}

	@Test
	void updateShouldBeNotFound() throws Exception {
		Long id = 1L;
		LoginModel loginModel = loginModel(id);
		when(employerService.updateProfile(any())).thenThrow(ApplicationException.noRecordFound(null));
		this.mockMvc
				.perform(put(REQUEST_PARAM_MAPPING + "/profile" + '/' + id)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(objectMapper.writeValueAsString(loginModel)).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isBadRequest()).andExpect((jsonPath("$.statusCode").value("404")));
	}

	@Test
	void shouldCreatEmplyerSuccessfully() throws Exception {
		LoginModel loginModel = loginModel();
		when(employerService.createLogin(any())).thenReturn(loginModel);
		this.mockMvc
				.perform(post(REQUEST_PARAM_MAPPING + "/profile" + '/').contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(loginModel)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.companyName").value(COMPANY_NAME));
	}

	private LoginModel loginModel() {
		Long id = 8L;
		LoginModel loginModel = new LoginModel();
		loginModel.setId(id);
		loginModel.setContactNo(32596325L);
		loginModel.setAddress("Kolkata");
		loginModel.setCompanyName("TCS");
		loginModel.setCompanyType("MNC");
		loginModel.setEmail("abc@gmail.com");
		loginModel.setLocation("Kolkata");
		loginModel.setPassword("Kolkata@2021");
		loginModel.setConfirmPassword("Kolkata@2021");
		loginModel.setFaxNo("5361456");
		return loginModel;
	}
}