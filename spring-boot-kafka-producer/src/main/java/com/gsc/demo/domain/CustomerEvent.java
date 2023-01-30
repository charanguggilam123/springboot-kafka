package com.gsc.demo.domain;

import java.time.LocalDate;

public class CustomerEvent {
	
	private String customerNumber;
	private String name;
	private LocalDate dob;
	private String email;
	private long mobile;
	private Address address;
	public CustomerEvent() {
		super();
	}
	public CustomerEvent(String customerNumber, String name, LocalDate dob, String email, long mobile, Address address) {
		super();
		this.customerNumber = customerNumber;
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public String getName() {
		return name;
	}
	public LocalDate getDob() {
		return dob;
	}
	public String getEmail() {
		return email;
	}
	public long getMobile() {
		return mobile;
	}
	public Address getAddress() {
		return address;
	}
	
	

}
