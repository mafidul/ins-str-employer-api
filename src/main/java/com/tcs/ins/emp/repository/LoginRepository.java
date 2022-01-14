package com.tcs.ins.emp.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tcs.ins.emp.repository.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long>, JpaSpecificationExecutor<Login> {

}