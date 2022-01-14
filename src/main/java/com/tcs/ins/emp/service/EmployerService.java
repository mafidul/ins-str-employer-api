package com.tcs.ins.emp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.tcs.ins.emp.service.model.EmployerSearchRequest;
import com.tcs.ins.emp.service.model.LoginDetailModel;
import com.tcs.ins.emp.service.model.LoginModel;

public interface EmployerService {

	LoginModel updateProfile(LoginModel source);

	LoginModel getProfileById(Long id);

	LoginModel createLogin(LoginModel loginModel);

	void deleteProfile(Long id);

	Page<LoginModel> searchEmployer(PageRequest pageRequest, EmployerSearchRequest searchRequest);

	LoginDetailModel login(LoginDetailModel loginModel);

	Page<LoginModel> findAll(Pageable pageable);
}
