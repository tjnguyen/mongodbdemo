package com.example.springbootaws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootaws.domain.Product;
import com.example.springbootaws.reposistory.ProductRepository;

@RestController
@RequestMapping(value="/demo")
public class ProductController 
{
   
   @Autowired
   private ProductRepository prodRepo;
  
  
   
   @GetMapping("/findproduct/{name}")
   public Product getProductbyName (@PathVariable("name")String name) throws DataAccessException
   {
	   Product product = null;
	   try
	   {
	      product = prodRepo.findByName(name);
	   }
	   catch(Exception ex)
	   {
		   System.out.println("catch exception " + ex.getMessage());
	   }
   	
       return product;
   }  
   
   @RequestMapping(method=RequestMethod.GET, value="/getAllProduct")
    public Iterable<Product> getProducts () throws DataAccessException
    {
	   Iterable<Product> products  = null;
	   try
	   {
	      System.out.println("retrieve all products");
	      products = prodRepo.findAll();
	      if (products != null)
	      {
	    	  for (Product prod:products)
	    	  {
	    		  System.out.println(prod.getName() + prod.getDescription() + prod.getPrice());
	    	  }
	      }
	   }
	   catch(Exception ex)
	   {
		   System.out.println("catch exception " + ex.getMessage());
	   }
    	
    	
    	return products;
    }  
   
  
	   
}
