package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.demo.domain.Address;
import com.example.demo.domain.Customer;
import com.example.demo.domain.Product;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ProductService;
import com.mongodb.MongoClient;

@SpringBootApplication
@EnableMongoRepositories
public class DemoApplication extends AbstractMongoConfiguration  implements CommandLineRunner  {

	@Autowired
	private ProductService prodService;
	
	@Autowired
	private CustomerService customerservice;
	
	
	public static void main(String[] args) {
		
		System.out.println(" thu demo");
		SpringApplication.run(DemoApplication.class, args);
	}
	
	
	@Override
	  protected String getDatabaseName() {
	    return "e-store";
	  }

	  @Override
	  public MongoClient mongoClient() {
	    return new MongoClient();
	  }

	  @Override
	  protected String getMappingBasePackage() {
	    return "com.example.demo";
	  }
	  
	  
	  @Override
      public void run(String... args) throws Exception 
      {
		  try
		 {
         System.out.println(" Running command lines");
         List<Product> products = new ArrayList<Product>();
         
         Product product = new Product("product1", "for sale ", 12);
         Product product2 = new Product("product2", "for demo 2", 33);
         Product product3 = new Product("product3", "for sale 3 ", 15);
         Product product4 = new Product("product4", "for sale 4 ", 18);
        
         
         products.add(product);
         products.add(product2);
         products.add(product3);
         products.add(product4);
         
         prodService.persistProducts(products);
         
         
         Address address1 = new Address("2105 Apple valley", "75023", "US");
         
         Address address2= new Address("777 Dallas Medical City", "75082", "US");
         
         Address address3 = new Address("88 Arapaho", "75082", "US");
         
         Address address4 = new Address("123 Jupiter", "75082", "US");
         
         Address address5 = new Address("666 Beltline", "75082", "US");
         
         List<Address> addresses1 = new ArrayList<Address>();
         List<Address> addresses2 = new ArrayList<Address>();
         List<Address> addresses3 = new ArrayList<Address>();
         
         
         addresses1.add(address5);
         
         
         
         addresses2.add(address2);
         addresses2.add(address3);
         
         addresses3.add(address1);
         addresses3.add(address4);
         
         customerservice.saveAddresses(addresses1);
         customerservice.saveAddresses(addresses2);
         customerservice.saveAddresses(addresses3);
         
         List<Customer> customers = new ArrayList<Customer>();
         
         Customer customer1 = new Customer("thu", "nguyen","thu.nguyen@yahoo.com");
         
         Customer customer2 = new Customer("tiffany", "nguyen", "tiff.nguyen@yahoo.com");
         
         Customer customer3 = new Customer("conner", "stewart", "tiff.nguyen@yahoo.com");
         
         customers.add(customer1);
         
         customers.add(customer2);
         
         customers.add(customer3);
         
         customer1.setAddress(addresses1);
         customer2.setAddress(addresses2);
         customer3.setAddress(addresses3);
         
         
         customerservice.persistCustomers(customers);
		 }
		  catch(Exception ex)
		  {
			  System.out.println(ex.getMessage());
		  }
         
      }
         

}
