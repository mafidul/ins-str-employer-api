package com.tcs.ins.emp.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.tcs.ins.emp.repository.entity.Login;
import com.tcs.ins.emp.service.model.LoginModel;

@Mapper(componentModel = "spring")
public interface EmployerMapper {

	Login toEntity(LoginModel source);

	List<LoginModel> toModel(Page<Login> source);

	LoginModel toModel(Login source);
	
	List<LoginModel> toModel(List<Login> source);

}
