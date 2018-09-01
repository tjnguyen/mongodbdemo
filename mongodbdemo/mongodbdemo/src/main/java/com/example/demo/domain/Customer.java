package com.example.demo.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
public class Customer
{
	@Id
	private String id;
	private String firstname;
	private String lastname;
	@DBRef
	private List<Address> address;
	
	private String  emailAddress;
	
	protected Customer() {};
	public Customer(String firstname, String lastname, String emailAddress)
	{
	   this.firstname = firstname;
	   this.lastname = lastname;
	   this.emailAddress = emailAddress;
	   
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	
	
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	
	

	

	
	
	
}
