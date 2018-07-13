package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;


@Service
public class ProductService 
{
   @Autowired
   private ProductRepository prodRepo;
   
   
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
   
   
   public Product findProduct(String id)
   {
	   Product product = null;
	   Optional<Product> prod = prodRepo.findById(id);
	   
	   if (prod.isPresent())
	   {
		 product = prod.get();
	     System.out.println("Prod name " + prod.get().getName() + " description: " + prod.get().getDescription() + 
	    		 " price: " + prod.get().getPrice());
	   }
	   
	   return product;
   }
   
   
  
  
  
}
