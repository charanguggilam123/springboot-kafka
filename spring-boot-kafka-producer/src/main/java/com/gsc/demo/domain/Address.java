package com.gsc.demo.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

public class Address {
	
	 @NotNull
	  @Length(max = 50)
	  private String addressLine1;
	  
	  private String addressLine2;
	  
	  private String street;
	  
	  @NotNull
	  @Size(max = 5,min = 5)
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


}
