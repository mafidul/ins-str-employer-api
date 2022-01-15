package com.tcs.ins.emp.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.tcs.ins.emp.service.EmployerService;
import com.tcs.ins.emp.service.model.LoginDetailModel;
import com.tcs.ins.emp.service.model.LoginModel;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

public class EmployerApiJMockitTest {

	@Injectable
	private EmployerService mockemployerService;

	@Tested
	private EmployerApi targetBeingTested;

	@Test
	void getCustomerByIdOptionalPresent() {
		Long id = 1L;

		new Expectations() {
			{
				mockemployerService.getProfileById(id);
				result = validLoginModel(id);
			}
		};

		ResponseEntity<LoginModel> customerModel = targetBeingTested.getProfileById(id);

		assertThat(customerModel).isNotNull();
	}

	@Test
	void EmplyerLoginSuccessful() {
		LoginDetailModel loginDetailModel = loginDetailModel();
		new Expectations() {
			{
				mockemployerService.login(loginDetailModel);
				result = loginDetailModel;
			}
		};

		ResponseEntity<LoginDetailModel> customerModel = targetBeingTested.login(loginDetailModel);

		assertThat(customerModel).isNotNull();
	}

	private LoginModel validLoginModel(Long id) {
		LoginModel loginModel = new LoginModel();
		loginModel.setId(1L);
		loginModel.setPassword("Admin@123");
		return loginModel;
	}

	private LoginDetailModel loginDetailModel() {
		Long id = 1L;
		LoginDetailModel loginDetailModel = new LoginDetailModel();
		loginDetailModel.setId(id);
		loginDetailModel.setPassword("Kolkata@2021");
		return loginDetailModel;
	}
}
