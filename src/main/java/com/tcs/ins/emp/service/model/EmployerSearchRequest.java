package com.tcs.ins.emp.service.model;

import java.util.Map;

import org.springframework.util.StringUtils;

public class EmployerSearchRequest {
	private static final String REQUEST_PARAM_ID = "id";
	private static final String REQUEST_PARAM_CONTACTNO = "contactNo";
	private static final String REQUEST_PARAM_COMPANY_NAME = "companyName";
	private static final String REQUEST_PARAM_EMAIL = "email";

	private final Long id;
	private final Long contactNo;

	private final String companyName;
	private final String email;

	public EmployerSearchRequest(Map<String, String> requestParam) {
		String idStr = requestParam.get(REQUEST_PARAM_ID);
		if (StringUtils.hasText(idStr)) {
			this.id = Long.valueOf(idStr);
		} else {
			this.id = null;
		}

		String contactNoStr = requestParam.get(REQUEST_PARAM_CONTACTNO);
		if (StringUtils.hasText(contactNoStr)) {
			this.contactNo = Long.valueOf(contactNoStr);
		} else {
			this.contactNo = null;
		}

		this.companyName = requestParam.get(REQUEST_PARAM_COMPANY_NAME);
		this.email = requestParam.get(REQUEST_PARAM_EMAIL);
	}

	public boolean idFilteringRequired() {
		return id != null;
	}

	public boolean contactNoFilteringRequired() {
		return contactNo != null;
	}

	public boolean companyNameFilteringRequired() {
		return StringUtils.hasText(companyName);
	}

	public boolean emailFilteringRequired() {
		return StringUtils.hasText(email);
	}

	public Long getId() {
		return id;
	}

	public Long getContactNo() {
		return contactNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getEmail() {
		return email;
	}
}
