package com.gsc.demo.entity;

import java.time.LocalDate;

public class Customer {
	
	private String customerNumber;
	private String name;
	private LocalDate dob;
	private String email;
	private long mobile;
	private Address address;
	public Customer() {
		super();
	}
	public Customer(String customerNumber, String name, LocalDate dob, String email, long mobile, Address address) {
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
	@Override
	public String toString() {
		return "Customer [customerNumber=" + customerNumber + ", name=" + name + ", dob=" + dob + ", email=" + email
				+ ", mobile=" + mobile + ", address=" + address + "]";
	}
	
	

}
