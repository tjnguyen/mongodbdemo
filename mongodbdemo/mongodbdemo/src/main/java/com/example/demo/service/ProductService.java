package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Customer;
import com.example.demo.domain.Product;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;


@Service
public class ProductService 
{
   private ProductRepository prodRepo;
   
   
   @Autowired
   public ProductService(ProductRepository prodRepo, CustomerRepository  customerRepo)
   {
	   this.prodRepo = prodRepo;
   }
   
   
   
   public void persistProducts(List<Product> products)
   {
	   System.out.println("persistProducts ");
	   
	      for (Product prod:products)
	      {
		     prodRepo.save(prod);
	      }
	  
   }
   
   
   
   public Iterable<Product>  findAllProducts()
   {
	   Iterable<Product> prods  = prodRepo.findAll();
	   
	   for(Product prod:prods)
	   {
		   System.out.println("Prod name " + prod.getName() + " description: " + prod.getDescription() + " price: " + prod.getPrice());
	   }
	   
	   return prods;
   }
   
   
   public Iterable<Product>  findSpecificProducts()
   {
	   Iterable<Product> prods  = prodRepo.getProductMoreExpensiveThan();
	   
	   for(Product prod:prods)
	   {
		   System.out.println("Prod name " + prod.getName() + " description: " + prod.getDescription() + " price: " + prod.getPrice());
	   }
	   
	   return prods;
   }
   
   public Product findProductByName(String name)
   {
	   Product prod = prodRepo.findByName(name);
	   
	   if (prod != null)
	   {
	     System.out.println("Prod name " + prod.getName() + " description: " + prod.getDescription() + 
	    		 " price: " + prod.getPrice());
	   }
	   
	   return prod;
   }
   
   
  
  
  
}
