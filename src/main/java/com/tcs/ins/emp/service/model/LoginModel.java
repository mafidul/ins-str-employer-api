package com.tcs.ins.emp.service.model;

import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.tcs.ins.emp.api.validation.ValidName;
import com.tcs.ins.emp.api.validation.ValidPassword;

public class LoginModel {

	private Long id;
	private Long contactNo;

	private String faxNo;
	@Email
	private String email;
	@ValidPassword
	private String password;
	@ValidPassword
	private String confirmPassword;
	@NotBlank
	@ValidName
	private String companyName;
	@NotBlank
	private String location;
	private String companyType;
	private String address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContactNo() {
		return contactNo;
	}

	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "LoginModel [id=" + id + ", contactNo=" + contactNo + ", faxNo=" + faxNo + ", email=" + email
				+ ", password=" + password + ", confirmPassword=" + confirmPassword + ", companyName=" + companyName
				+ ", location=" + location + ", companyType=" + companyType + ", address=" + address + "]";
	}
}
