package com.tcs.ins.emp.api;

import static com.tcs.ins.support.Constant.REQUEST_PARAM_MAPPING;
import static com.tcs.ins.support.Constant.REQUEST_PARAM_PAGE_NUMBER;
import static com.tcs.ins.support.Constant.REQUEST_PARAM_PAGE_SIZE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.ins.emp.service.DefaultEmployerService;
import com.tcs.ins.emp.service.EmployerService;
import com.tcs.ins.emp.service.model.LoginDetailModel;
import com.tcs.ins.emp.service.model.LoginModel;
import com.tcs.ins.exception.ApplicationException;

import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(EmployerApi.class)
@AutoConfigureMockMvc(addFilters = false)
public class EmployerApiMVCTest {

	private static final String COMPANY_NAME = "TCS";

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEmployerService.class);

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
		when(employerService.getProfileById(anyLong()))
				.thenThrow(ApplicationException.noRecordFound("No Record Found"));
		this.mockMvc
				.perform(get(REQUEST_PARAM_MAPPING + "/profile" + '/' + id)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	void EmplyerLoginSuccessful() throws Exception {
		Long id = 1L;
		LoginDetailModel loginDetailModel = loginDetailModel();
		when(employerService.login(any())).thenReturn(loginDetailModel);

		this.mockMvc
				.perform(post(REQUEST_PARAM_MAPPING + "/login" + '/').contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(loginDetailModel)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id").value(id));
	}

	@Test
	void EmplyerNotLoginSuccessful() throws Exception {
		LoginDetailModel loginDetailModel = loginDetailModel();

		when(employerService.login(any())).thenThrow(ApplicationException.noRecordFound("Invalid Password"));

		this.mockMvc
				.perform(post(REQUEST_PARAM_MAPPING + "/login" + '/').contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(loginDetailModel)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect((jsonPath("$.statusCode").value("404")));
	}

	private LoginDetailModel loginDetailModel() {
		Long id = 1L;
		LoginDetailModel loginDetailModel = new LoginDetailModel();
		loginDetailModel.setId(id);
		loginDetailModel.setPassword("Kolkata@2021");
		return loginDetailModel;
	}

	@Test
	void shouldCreatEmplyerSuccessfully() throws Exception {
		LoginModel loginModel = loginModel();
		String writeValueAsString = objectMapper.writeValueAsString(loginModel);
		LOGGER.info("checking the value from Json: {}" + writeValueAsString);
		when(employerService.createLogin(any())).thenReturn(loginModel);
		this.mockMvc
				.perform(post(REQUEST_PARAM_MAPPING + "/profile" + '/').contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(loginModel)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.companyName").value(COMPANY_NAME));
	}

	private LoginModel loginModel() {
		Long id = 1L;
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

	@Test
	void shouldNotCreatEmplyerSuccessfully() throws Exception {
		LoginModel loginModel = loginModel();
		String message = "Duplicate Record";
		when(employerService.createLogin(any())).thenThrow(ApplicationException.duplicateRecord(message));
		this.mockMvc
				.perform(post(REQUEST_PARAM_MAPPING + "/profile" + '/').contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(loginModel)).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.statusCode").value("400")).andExpect(jsonPath("$.errorMessage").value(message));
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
	void deleteShouldBeSuccessfully() throws Exception {
		Long id = 1L;
		LoginModel loginModel = loginModel(id);
		doNothing().when(employerService).deleteProfile(id);

		this.mockMvc
				.perform(delete(REQUEST_PARAM_MAPPING + "/profile" + '/' + id)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(objectMapper.writeValueAsString(loginModel)).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void deleteShouldNotBeSuccessfully() throws Exception {
		Long id = 1L;
		LoginModel loginModel = loginModel(id);
		doNothing().when(employerService).deleteProfile(id);
		this.mockMvc
				.perform(delete(REQUEST_PARAM_MAPPING + "/profile" + '/' + id)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(objectMapper.writeValueAsString(loginModel)).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void allPageShouldBeFound() throws Exception {
		int pageNumber = 1, pageSize = 10;
		LoginModel loginModel = loginModel();
		when(employerService.searchEmployer(any(), any())).thenReturn(new PageImpl<>(Collections.singletonList(loginModel)));
		LOGGER.info("Return Value from then {} " + new PageImpl<>(Collections.emptyList()));
		this.mockMvc
				.perform(get(REQUEST_PARAM_MAPPING + "/profile" + '/' + "search")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.param(REQUEST_PARAM_PAGE_NUMBER, String.valueOf(pageNumber))
						.param(REQUEST_PARAM_PAGE_SIZE, String.valueOf(pageSize)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.content").isArray());
	}

//	@Test
//	void allPageShouldBeFoundError() throws Exception {
//		int pageNumber = 0, pageSize = 10;
//		// LoginModel loginModel = loginModel();
//		when(employerService.searchEmployer(any(), any()))
//				.thenThrow(ApplicationException.invalidRecord("Invalid Record Found"));
//
//		this.mockMvc
//				.perform(get(REQUEST_PARAM_MAPPING + "/profile" + '/' + "search")
//						.contentType(MediaType.APPLICATION_JSON_VALUE)
//						.param(REQUEST_PARAM_PAGE_NUMBER, String.valueOf(pageNumber))
//						.param(REQUEST_PARAM_PAGE_SIZE, String.valueOf(pageSize)))
//				.andDo(print()).andExpect(status().isBadRequest())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(jsonPath("$").exists());
//	}

//	@Test
//	void allPageShouldNotBeFound() throws Exception {
//		int pageNumber = 0, pageSize = 10;
//		// LoginModel loginModel = loginModel();
//		when(employerService.searchEmployer(any(), any())).thenReturn(new PageImpl<>(Collections.emptyList()));
//
//		this.mockMvc
//				.perform(get(REQUEST_PARAM_MAPPING + "/profile" + '/' + "search")
//						.contentType(MediaType.APPLICATION_JSON_VALUE)
//						.param(REQUEST_PARAM_PAGE_NUMBER, String.valueOf(pageNumber))
//						.param(REQUEST_PARAM_PAGE_SIZE, String.valueOf(pageSize)))
//				.andDo(print()).andExpect(status().isOk())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(jsonPath("$").exists())
//				.andExpect(status().isOk());
//	}
}