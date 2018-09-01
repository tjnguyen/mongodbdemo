package com.example.demo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Address 
{
	@Id
	private String id;
	private String street;
	private String zipcode;
	private String country;
	
	
	protected Address() {}

    public Address(String street, String zipcode, String country)	
    {
    	this.street = street;
    	this.zipcode = zipcode;
    	this.country = country;
    }

    


	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}


	
    

	
	
}
