package com.example.demo.model;

public class Account {
	private String name;
	private String email;
	private String password;
	
	public Account() {
		
	}
	
	public Account(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	public Account(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
}
