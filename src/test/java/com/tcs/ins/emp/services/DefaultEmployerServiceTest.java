package com.tcs.ins.emp.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.tcs.ins.emp.repository.LoginRepository;
import com.tcs.ins.emp.repository.entity.Login;
import com.tcs.ins.emp.service.DefaultEmployerService;
import com.tcs.ins.emp.service.mapper.EmployerMapper;
import com.tcs.ins.emp.service.model.LoginDetailModel;
import com.tcs.ins.emp.service.model.LoginModel;
import com.tcs.ins.emp.service.specification.EmployerSpecification;
import com.tcs.ins.exception.ApplicationException;

@ExtendWith({ MockitoExtension.class })
public class DefaultEmployerServiceTest {

	@Mock
	private EmployerMapper mockEmployerMapper;
	@Mock
	private LoginRepository mockLoginRepository;

	@InjectMocks
	private DefaultEmployerService targetBeingTested;

	@Test
	void getProfileByIdTest() {
		Long id = 1L;
		LoginModel loginModel = getLoginProfileById(id);
		when(mockEmployerMapper.toModel((Login) any())).thenReturn(loginModel);
		
		when(mockLoginRepository.findById(id)).thenReturn(Optional.of(getLoginModelProfileById(id)));
		LoginModel result = targetBeingTested.getProfileById(1L);
		assertThat(result.getId()).isEqualTo(id);
	}

	private Login getLoginModelProfileById(Long id) {
		Login login = new Login();
		login.setId(id);
		login.setAddress("Kolkata");
		login.setCompanyName("TCS");
		login.setCompanyType("MNC");
		login.setContactNo(32596325L);
		login.setEmail("abc@gmail.com");
		login.setLocation("Kolkata");
		login.setPassword("Kolkata");
		login.setConfirmPassword("Kolkata");
		login.setFaxNo("5361456");
		return login;
	}

	private LoginModel getLoginProfileById(Long id) {
		LoginModel loginModel = new LoginModel();
		loginModel.setId(id);
		loginModel.setAddress("Kolkata");
		loginModel.setCompanyName("TCS");
		loginModel.setCompanyType("MNC");
		loginModel.setContactNo(32596325L);
		loginModel.setEmail("abc@gmail.com");
		loginModel.setLocation("Kolkata");
		loginModel.setPassword("Kolkata");
		loginModel.setConfirmPassword("Kolkata");
		loginModel.setFaxNo("5361456");
		return loginModel;
	}

	private LoginModel getLoginModelNullById(Long id) {
		LoginModel loginModel = new LoginModel();
		loginModel.setId(id);
		loginModel.setAddress("");
		loginModel.setCompanyName("");
		loginModel.setCompanyType("");
		loginModel.setContactNo(null);
		loginModel.setEmail("");
		loginModel.setLocation("");
		loginModel.setPassword("");
		loginModel.setConfirmPassword("");
		loginModel.setFaxNo("");
		return loginModel;
	}

	@Test
	void createLoginTest() {
		Long id = 1L;
		LoginModel loginModel = getLoginProfileById(id);
		when(mockEmployerMapper.toModel((Login) any())).thenReturn(loginModel);
		when(mockLoginRepository.save(any())).thenReturn(getLoginModelProfileById(id));
		LoginModel result = targetBeingTested.createLogin(loginModel);
		assertThat(result.getId()).isEqualTo(id);
	}

	@Test
	void updateProfileTest() {
		Long id = 1L;
		LoginModel loginModel = getLoginProfileById(id);
		LoginModel loginModelNull = getLoginModelNullById(id);
		when(mockLoginRepository.findById(id)).thenReturn(Optional.of(getLoginModelProfileById(id)));
		when(mockEmployerMapper.toModel((Login) any())).thenReturn(loginModel);
		when(mockLoginRepository.save(any())).thenReturn(getLoginModelProfileById(id));
		LoginModel result = targetBeingTested.updateProfile(loginModel);
		assertThat(result.getId()).isEqualTo(id);

		when(mockEmployerMapper.toModel((Login) any())).thenReturn(loginModelNull);
		LoginModel resultNull = targetBeingTested.updateProfile(loginModelNull);
		assertThat(resultNull.getId()).isEqualTo(id);
	}

	@Test
	void deleteProfileTest() {
		Long id = 1L;
		targetBeingTested.deleteProfile(id);
	}

	@Test
	void loginTest() {
		Long id = 1L;
		LoginDetailModel loginDetailModel = employerWithPasswordOne(id);
		LoginDetailModel loginModel = getLoginModelDetailProfileById(id);
		when(mockLoginRepository.findById(id)).thenReturn(Optional.of(getLoginModelProfileById(id)));
		LoginDetailModel result = targetBeingTested.login(loginModel);
		assertThat(result.getId()).isEqualTo(id);
		assertThrows(ApplicationException.class, () -> {
			targetBeingTested.login(loginDetailModel);
		});
	}

	private LoginDetailModel getLoginModelDetailProfileById(Long id) {
		LoginDetailModel loginDetailModel = new LoginDetailModel();
		loginDetailModel.setId(id);
		loginDetailModel.setPassword("Kolkata");
		return loginDetailModel;
	}

	@SuppressWarnings("unchecked")
	@Test
	void searchEmployerTest() {
		when(mockLoginRepository.findAll((EmployerSpecification) any(), (PageRequest) any())).thenReturn(newPageLogin());
		when(mockEmployerMapper.toModel((Page<Login>) any())).thenReturn(Collections.emptyList());
		Page<LoginModel> searchEmployer = targetBeingTested.searchEmployer(PageRequest.of(1, 1), null);
		assertThat(searchEmployer.getSize()).isEqualTo(1);
	}

	private Page<Login> newPageLogin() {
		return new PageImpl<>(Collections.emptyList(), PageRequest.of(1, 1), 1);
	}

	private LoginDetailModel employerWithPasswordOne(Long id) {
		LoginDetailModel loginDetailModel = new LoginDetailModel();
		loginDetailModel.setId(id);
		loginDetailModel.setPassword("Admin@2020");
		return loginDetailModel;
	}

	@Test
	void createLoginPasswordTest() {
		Long id = 1L;
		LoginModel loginModel = getProfileByPassword(id);
		assertThrows(ApplicationException.class, () -> {
			targetBeingTested.createLogin(loginModel);
		});
	}

	@Test
	void updateLoginPasswordTest() {
		Long id = 1L;
		when(mockLoginRepository.findById(id)).thenReturn(Optional.of(getLoginModelProfileById(id)));
		LoginModel loginModel = getProfileByPassword(id);
		assertThrows(ApplicationException.class, () -> {
			targetBeingTested.updateProfile(loginModel);
		});
	}

	private LoginModel getProfileByPassword(Long id) {
		LoginModel loginModel = new LoginModel();
		loginModel.setId(id);
		loginModel.setAddress("Kolkata");
		loginModel.setCompanyName("TCS");
		loginModel.setCompanyType("MNC");
		loginModel.setContactNo(32596325L);
		loginModel.setEmail("abc@gmail.com");
		loginModel.setLocation("Kolkata");
		loginModel.setPassword("Kolkata1");
		loginModel.setConfirmPassword("Kolkata");
		loginModel.setFaxNo("5361456");
		return loginModel;
	}

	@Test
	void getProfilePasswordTest() {
		Long id = 1L;
		when(mockLoginRepository.findById(id)).thenReturn(Optional.empty());
		LoginModel loginModel = getLoginModelNullById(id);
		assertThrows(ApplicationException.class, () -> {
			targetBeingTested.updateProfile(loginModel);
		});
	}
}