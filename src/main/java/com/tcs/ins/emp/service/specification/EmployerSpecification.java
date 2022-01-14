package com.tcs.ins.emp.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.tcs.ins.emp.repository.entity.Login;
import com.tcs.ins.emp.service.model.EmployerSearchRequest;

public class EmployerSpecification implements Specification<Login> {
	private static final long serialVersionUID = 1L;

	private final EmployerSearchRequest employerSearchRequest;

	public EmployerSpecification(EmployerSearchRequest employerSearchRequest) {
		this.employerSearchRequest = employerSearchRequest;
	}

	@Override
	public Predicate toPredicate(Root<Login> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<>();
		if (employerSearchRequest.idFilteringRequired()) {
			predicates.add(criteriaBuilder.equal(root.get("id"), employerSearchRequest.getId()));
		}
		if (employerSearchRequest.contactNoFilteringRequired()) {
			predicates.add(criteriaBuilder.equal(root.get("contactNo"), employerSearchRequest.getContactNo()));
		}
		if (employerSearchRequest.companyNameFilteringRequired()) {
			predicates.add(criteriaBuilder.like(root.get("companyName"), employerSearchRequest.getCompanyName() + "%"));
		}
		if (employerSearchRequest.emailFilteringRequired()) {
			predicates.add(criteriaBuilder.like(root.get("email"), employerSearchRequest.getEmail() + "%"));
		}

		return andTogether(predicates, criteriaBuilder);
	}

	private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
		return cb.and(predicates.toArray(new Predicate[0]));
	}
}