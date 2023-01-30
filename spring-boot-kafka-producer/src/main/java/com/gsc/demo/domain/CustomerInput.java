package com.gsc.demo.domain;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerInput {
	
	static final String ERROR="Please provide data that starts with 'C' followed by 9 digits";
	
	@JsonProperty("customerNumber") //needed only if getters not given
	@NotNull
	@Size(min = 10, max = 10)
	@Pattern(regexp = "^C\\d{9}$",message =ERROR )
	private String customerNumber;

	@JsonProperty("firstName")
	@NotNull
	@Size(min = 5, max = 50)
	private String firstName;

	@JsonProperty("lastName")
	@NotBlank
	@Size(min = 5, max = 50)
	private String lastName;

/*	@JsonProperty("birthDate")
	@NotNull
	@PastOrPresent
//	@Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$")
	private LocalDate birthDate;*/

	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@PastOrPresent
	private LocalDate birthDate;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Pattern(regexp = "^\\d{10}$",message = "Mobile Number should consist of 10 digits")
	private String mobileNumber;
	
	@Valid
	@NotNull
	private Address address;

	public String getCustomerNumber() {
		return customerNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public String getEmail() {
		return email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public Address getAddress() {
		return address;
	}
	

}
