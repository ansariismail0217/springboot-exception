package com.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{
	
	boolean existsByName(String name);	
	boolean existsByEmail(String email);
	boolean existsByMobNum(Long mobNum);

}
