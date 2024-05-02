package com.customer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	@Column(unique = true)
	private Long mobNum;
	
	@Column(unique = true)
	private String email;
	
	private String Address;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, Long mobNum, String email, String address) {
		super();
		this.name = name;
		this.mobNum = mobNum;
		this.email = email;
		Address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMobNum() {
		return mobNum;
	}

	public void setMobNum(Long mobNum) {
		this.mobNum = mobNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", mobNum=" + mobNum + ", email=" + email + ", Address="
				+ Address + "]";
	}

}
