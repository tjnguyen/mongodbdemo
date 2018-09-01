package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Address;
import com.example.demo.domain.Customer;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService 
{
	@Autowired
	CustomerRepository  customerRepo;
	
	@Autowired
	AddressRepository   addressRepository;
	
	public void persistCustomers(List<Customer> customers)
	   {
		   System.out.println("persistCustomer");
		   
		      for (Customer customer:customers)
		      {
			     customerRepo.save(customer);

		      }
	   }
	   
	   
	   
	   public Iterable<Customer>  findAllCustomers()
	   {
		   Iterable<Customer> customers  = customerRepo.findAll();
		   
		   for(Customer customer:customers)
		   {
			   System.out.println("Customer name " + customer.getFirstname() + " address: " + customer.getAddress()+ " email: " + customer.getEmailAddress());
		   }
		   
		   return customers;
	   }
	   
	   
	  public List<Customer>  findCustomerByEmail(String email)
	   {
		   List<Customer> customers  = customerRepo.findByEmailAddress(email);
		   
		   for(Customer customer:customers)
		   {
			   System.out.println("Customer name " + customer.getFirstname() + " email: " + customer.getEmailAddress());
		   }
		   
		   return customers;
	   }
	   
	    
	  
	   public List<Customer>  findCustomerAddress(Address address)
	   {
		   List<Customer> customers  = customerRepo.findByAddress(address);
		   
		   for(Customer customer:customers)
		   {
			   System.out.println("Customer name " + customer.getFirstname() + " address: " + customer.getAddress());
		   }
		   
		   return customers;
	   }
	   
	   public void saveAddresses(List<Address> addresses)
	   {
		   for (Address addr:addresses)
		   {
		      addressRepository.save(addr);
		   }
	   }
	   
	   public String findAddressWithQuery( String street, String zipcode)
	   {
		   System.out.println("enter findAddressWithQuery ");
		   String addr = null;
		   Address address = addressRepository.findAddressWithQuery(zipcode, street);
		   
		   if (address != null)
		   {
			   addr = address.getStreet() + address.getZipcode() + address.getCountry();
		   }
		   return addr;
	   }
	   
	   public String findByStreetAndZipcode(String street, String zipcode)
	   {
		   String addr = null;
		   Address address = addressRepository.findByStreetAndZipcode(street, zipcode);
		   
		   if (address != null)
		   {
			   addr = address.getStreet() + address.getZipcode() + address.getCountry();
		   }
		   return addr;
	   }
	  
	   
	   
}
