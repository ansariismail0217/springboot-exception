package com.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.customer.exception.CustomerAlreadyExistsException;
import com.customer.exception.NoSuchCustomerExistsException;
import com.customer.model.Customer;
import com.customer.repository.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	public Customer addCustomer(@RequestBody Customer customer) {

//		check if customer already exist
		if (customerRepo.existsByMobNum(customer.getMobNum())) {
			throw new CustomerAlreadyExistsException(
					"Customer already exists with the mobile number- " + customer.getMobNum());
		}

		else if (customerRepo.existsByEmail(customer.getEmail())) {
			throw new CustomerAlreadyExistsException("Customer already exists with email- " + customer.getEmail());
		}

		else {
			return customerRepo.save(customer);
		}
	}

	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}
	
	public Customer getCustomerById(@PathVariable int id) {
		return customerRepo.findById(id)
				.orElseThrow(() -> new NoSuchCustomerExistsException("No customer present with id = " + id));
	}
	
	public ResponseEntity<Customer> updateCustomerById(@RequestBody Customer customer, @PathVariable int id) {
		if (customerRepo.existsById(id)) {
			Customer existingCustomer = customerRepo.findById(id).get();
			existingCustomer.setName(customer.getName());
			existingCustomer.setMobNum(customer.getMobNum());
			existingCustomer.setEmail(customer.getEmail());
			existingCustomer.setAddress(customer.getAddress());
			customerRepo.save(existingCustomer);
			return ResponseEntity.ok(existingCustomer);
		} else {
			throw new NoSuchCustomerExistsException("No Such Customer exists!");
		}
	}
	
	public void deleteCustomerById(@PathVariable int id) {
		customerRepo.deleteById(id);
	}

}
