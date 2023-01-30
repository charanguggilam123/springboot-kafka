package com.gsc.demo.entity;

public class Address {

	private String addressLine1;

	private String addressLine2;

	private String street;

	private String postalCode;

	public String getAddressLine1() {
		return addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public String getStreet() {
		return street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	@Override
	public String toString() {
		return "Address [addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", street=" + street
				+ ", postalCode=" + postalCode + "]";
	}

	
}
