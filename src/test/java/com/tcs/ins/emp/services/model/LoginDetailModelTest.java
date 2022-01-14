package com.tcs.ins.emp.services.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tcs.ins.emp.service.model.LoginDetailModel;

@ExtendWith({ MockitoExtension.class })
public class LoginDetailModelTest {

	@Test
	void toStringTest() {
		LoginDetailModel loginModel = new LoginDetailModel();
		loginModel.setId(1L);
		assertThat(loginModel.toString()).isNotEmpty();
	}
}
