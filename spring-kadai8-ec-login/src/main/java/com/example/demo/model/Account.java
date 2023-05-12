package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.entity.Customer;

@Component
@SessionScope
public class Account {

//	private String name;
	private Customer customer;
	
	public Account() {
		
	}

	public Account(Customer customer) {
		this.customer = customer;
		this.customer.setPassword("");
	}
	
	public Customer getCustomer() {
		return customer;
	}

//	public Account(String name) {
//		this.name = name;
//	}

//	public String getName() {
//		return name;
//	}

//	public void setName(String name) {
//		this.name = name;
//	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
