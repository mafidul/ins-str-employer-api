package com.tcs.ins.emp.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tcs.ins.emp.repository.LoginRepository;
import com.tcs.ins.emp.repository.entity.Login;
import com.tcs.ins.emp.service.mapper.EmployerMapper;
import com.tcs.ins.emp.service.model.EmployerSearchRequest;
import com.tcs.ins.emp.service.model.LoginDetailModel;
import com.tcs.ins.emp.service.model.LoginModel;
import com.tcs.ins.emp.service.specification.EmployerSpecification;
import com.tcs.ins.exception.ApplicationException;

@Service
public class DefaultEmployerService implements EmployerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEmployerService.class);

	private final EmployerMapper employerMapper;
	private final LoginRepository loginRepository;

	public DefaultEmployerService(EmployerMapper employerMapper, LoginRepository loginRepository) {
		this.employerMapper = employerMapper;
		this.loginRepository = loginRepository;
	}

	@Override
	public LoginModel getProfileById(Long id) {
		Login login = getOrThrow(id);
		return employerMapper.toModel(login);
	}

	private Login getOrThrow(Long id) {
		Optional<Login> optionalNewPost = loginRepository.findById(id);
		if (!(optionalNewPost.isPresent())) {
			throw ApplicationException.noRecordFound("No Record Found");
		}
		return optionalNewPost.get();
	}

	@Override
	public LoginModel createLogin(LoginModel loginModel) {
		if (!(loginModel.getPassword()).equals((loginModel.getConfirmPassword()))) {
			throw ApplicationException.unAuthorized("Password and confirm passowrd didn't match");
		}
		Login newPost = employerMapper.toEntity(loginModel);
		Login createNewPost = loginRepository.save(newPost);
		return employerMapper.toModel(createNewPost);
	}

	@Override
	public LoginModel updateProfile(LoginModel loginModel) {
		Login newPost = getOrThrow(loginModel.getId());

		if (StringUtils.hasText(loginModel.getLocation())) {
			newPost.setLocation(loginModel.getLocation());
		}
		if (StringUtils.hasText(loginModel.getEmail())) {
			newPost.setEmail(loginModel.getEmail());
		}
		if (StringUtils.hasText(loginModel.getAddress())) {
			newPost.setAddress(loginModel.getAddress());
		}
		if (StringUtils.hasText(loginModel.getCompanyName())) {
			newPost.setCompanyName(loginModel.getCompanyName());
		}

		if (StringUtils.hasText(loginModel.getCompanyType())) {
			newPost.setCompanyType(loginModel.getCompanyType());
		}
		if (StringUtils.hasText(loginModel.getPassword())) {
			newPost.setPassword(loginModel.getPassword());
		}
		if (StringUtils.hasText(loginModel.getConfirmPassword())) {
			newPost.setConfirmPassword(loginModel.getConfirmPassword());
		}
		if (StringUtils.hasText(loginModel.getFaxNo())) {
			newPost.setFaxNo(loginModel.getFaxNo());
		}
		if (loginModel.getContactNo() != null) {
			newPost.setContactNo(loginModel.getContactNo());
		}
		if (!(loginModel.getPassword()).equals((loginModel.getConfirmPassword()))) {
			throw ApplicationException.unAuthorized("Password and confirm passowrd didn't match");
		}
		Login createNewPost = loginRepository.save(newPost);
		return employerMapper.toModel(createNewPost);

	}

	@Override
	public void deleteProfile(Long id) {
		loginRepository.deleteById(id);
	}

	@Override
	public Page<LoginModel> searchEmployer(PageRequest pageRequest, EmployerSearchRequest searchRequest) {
		Page<Login> page = loginRepository.findAll(new EmployerSpecification(searchRequest), pageRequest);
		List<LoginModel> content = employerMapper.toModel(page);
		return new PageImpl<>(content, pageRequest, page.getTotalElements());
	}

	@Override
	public LoginDetailModel login(LoginDetailModel loginModel) {
		Optional<Login> optionalLogin = loginRepository.findById(loginModel.getId());
		Login login = optionalLogin.get();
		LOGGER.info("value of Id from repositorry: {} " + login.getId());
		LOGGER.info("value of Id from model: {} " + loginModel.getId());
		if (!login.getPassword().equals(loginModel.getPassword())) {
			throw ApplicationException.invalidRecord("Invalid Password");
		}
		return loginModel;
	}

	@Override
	public Page<LoginModel> findAll(Pageable pageable) {
		return new PageImpl<>(employerMapper.toModel(loginRepository.findAll(pageable).getContent()));
	}

}
