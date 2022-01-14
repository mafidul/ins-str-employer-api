package com.tcs.ins.emp.service.model;

public class LoginDetailModel {

	private Long id;
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDetailModel [id=" + id + ", password=" + password + "]";
	}
}
