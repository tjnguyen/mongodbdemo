package com.example.demo.domain;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product 
{
	@Id
	private String id;
	
	@Size(min = 2, max = 14)
	private String name;
	
    private String description;
   
    private int price;
   
  
   
   protected Product() {}
   
   public Product(String name, String description, int price)
   {
	   this.name = name;
	   this.description = description;
	   this.price = price;
   }
   
  

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public int getPrice() {
	return price;
}

public void setPrice(int price) {
	this.price = price;
}





   
   
}
