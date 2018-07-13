package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner  {

	@Autowired
	private ProductService prodService;
	
	public static void main(String[] args) {
		
		System.out.println(" thu demo");
		SpringApplication.run(DemoApplication.class, args);
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
        
		 }
		  catch(Exception ex)
		  {
			  System.out.println(ex.getMessage());
		  }
         
      }
         

}
