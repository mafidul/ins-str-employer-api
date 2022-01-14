package com.tcs.ins.emp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import com.tcs.ins.emp.service.EmployerService;
import com.tcs.ins.emp.service.model.LoginDetailModel;
import com.tcs.ins.emp.service.model.LoginModel;

@ExtendWith({ MockitoExtension.class })
public class EmployerApiTest {

	@Mock
	private EmployerService mockemployerService;

	@InjectMocks
	private EmployerApi targetBeingTested;

	@Test
	void getProfileByIdTest() {
		Long id = 1L;
		LoginModel loginDetailModel = validLoginModel(id);
		when(mockemployerService.getProfileById(any())).thenReturn(loginDetailModel);
		ResponseEntity<LoginModel> result = targetBeingTested.getProfileById(id);
		assertThat(result.getBody().getId()).isEqualTo(1L);
	}

	private LoginModel validLoginModel(Long id) {
		LoginModel loginModel = new LoginModel();
		loginModel.setId(1L);
		loginModel.setPassword("Admin@123");
		return loginModel;
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void searchEmployerDESCTest() {
		Long id = 1L;
		Integer pageNumbr = 1;
		Integer pageSize = 10;
		LoginModel loginModel = validLoginModel(id);
		when(mockemployerService.searchEmployer(any(), any())).thenReturn(new PageImpl<>(Collections.singletonList(loginModel)));
		ResponseEntity<Page<LoginModel>> result = targetBeingTested.searchEmployer(pageNumbr, pageSize, "id", "DESC", validMap());
		assertThat(result.getHeaders().containsKey(id));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void searchEmployerASCTest() {
		Long id = 1L;
		Integer pageNumbr = 1;
		Integer pageSize = 10;
		LoginModel loginModel = validLoginModel(id);
		when(mockemployerService.searchEmployer(any(), any())).thenReturn(new PageImpl<>(Collections.singletonList(loginModel)));
		ResponseEntity<Page<LoginModel>> result = targetBeingTested.searchEmployer(pageNumbr, pageSize, "id", "ASC", validMap());
		assertThat(result.getHeaders().containsKey(id));
	}

	@Test
	void searchEmployerNotNullTest() {
		Long id = 1L;
		Integer pageNumbr = 1;
		Integer pageSize = 10;
		LoginModel loginModel = validLoginModel(id);
		when(mockemployerService.searchEmployer(any(), any())).thenReturn(new PageImpl<>(Collections.singletonList(loginModel)));
		ResponseEntity<Page<LoginModel>> result = targetBeingTested.searchEmployer(pageNumbr, pageSize, null, null,	validMap());
		assertThat(result.getBody().getTotalPages()).isEqualTo(1);
	}

	@Test
	void searchEmployerNullTest() {
		Long id = 1L;
		Integer pageNumbr = 1;
		Integer pageSize = 10;
		LoginModel loginModel = validLoginModel(id);
		when(mockemployerService.searchEmployer(any(), any())).thenReturn(new PageImpl<>(Collections.singletonList(loginModel)));
		ResponseEntity<Page<LoginModel>> result1 = targetBeingTested.searchEmployer(pageNumbr, pageSize, "id", null, validMap());
		assertThat(result1.getBody().getNumberOfElements()).isEqualTo(1);
	}

	private Map<String, String> validMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "1");
		return map;
	}

	@Test
	void loginTest() {
		Long id = 1L;
		LoginDetailModel loginDetailModel = validLoginDetailModel(id);
		when(mockemployerService.login(any())).thenReturn(loginDetailModel);
		ResponseEntity<LoginDetailModel> result = targetBeingTested.login(loginDetailModel);
		assertThat(result.getBody().getId()).isEqualTo(1L);
	}

	private LoginDetailModel validLoginDetailModel(Long id) {
		LoginDetailModel loginDetailModel = new LoginDetailModel();
		loginDetailModel.setId(1L);
		loginDetailModel.setPassword("Admin@123");
		return loginDetailModel;
	}

//	@Test
//	void createLoginTest() {
//		Long id = 1L;
//		LoginModel loginlModel = validLoginlModel(id);
//		when(mockemployerService.createLogin(any())).thenReturn(loginlModel);
//		ResponseEntity<LoginModel> result = targetBeingTested.createLogin(loginlModel);
//		assertThat(result.getBody().getId()).isEqualTo(1L);
//	}

	private LoginModel validLoginlModel(Long id) {
		LoginModel loginModel = new LoginModel();
		loginModel.setId(1L);
		loginModel.setPassword("Admin@123");
		return loginModel;
	}

	@Test
	void updateProfileTest() {
		Long id = 1L;
		LoginModel loginlModel = validLoginlModel(id);
		when(mockemployerService.updateProfile(any())).thenReturn(loginlModel);
		ResponseEntity<LoginModel> result = targetBeingTested.updateProfile(id, loginlModel, null);
		assertThat(result.getBody().getId()).isEqualTo(id);
	}

	@Test
	void deletePostTest() {
		Long id = 1L;
		doNothing().when(mockemployerService).deleteProfile(id);
		ResponseEntity<Void> result = targetBeingTested.deleteNewPost(id);
		assertThat(result.getBody());
	}
}
