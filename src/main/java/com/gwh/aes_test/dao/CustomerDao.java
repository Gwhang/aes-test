package com.gwh.aes_test.dao;

import com.gwh.aes_test.entity.TestCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 客户注解
 */
@Repository
public interface CustomerDao extends JpaRepository<TestCustomer,Integer> {

    TestCustomer findById(int id);

}
