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

import com.customer.exception.NoSuchCustomerExistsException;
import com.customer.model.Customer;
import com.customer.service.CustomerService;

@RestController
@RequestMapping("customer/")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@PostMapping("/add")
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}

	@GetMapping("/all")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable int id) {
		return customerService.getCustomerById(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Customer> updateCustomerById(@RequestBody Customer customer, @PathVariable int id) {
		return customerService.updateCustomerById(customer, id);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCustomerById(@PathVariable int id) {
		Customer existingCustomer = customerService.getCustomerById(id);
		if (existingCustomer == null) {
			throw new NoSuchCustomerExistsException("No Such Customer exists!");
		}

		else {
			customerService.deleteCustomerById(id);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	}

}
