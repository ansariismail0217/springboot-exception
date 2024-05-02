package com.customer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.exception.CustomerAlreadyExistsException;
import com.customer.exception.NoSuchCustomerExistsException;
import com.customer.model.Customer;
import com.customer.repository.CustomerRepo;

@RestController
@RequestMapping("customer/")
public class CustomerController {

	@Autowired
	private CustomerRepo customerRepo;

	@PostMapping("/add")
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

	@GetMapping("/all")
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable int id) {
		return customerRepo.findById(id)
				.orElseThrow(() -> new NoSuchCustomerExistsException("No customer present with id = " + id));
	}

	@PutMapping("/update/{id}")
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

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCustomerById(@PathVariable int id) {
		Customer existingCustomer = customerRepo.findById(id).orElse(null);
		if (existingCustomer == null) {
			throw new NoSuchCustomerExistsException("No Such Customer exists!");
		}

		else {
			customerRepo.deleteById(id);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	}

}
