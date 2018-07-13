package com.example.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class RepoTest {
	
	@Autowired
	private ProductService prodSvc;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private ProductRepository  prodRepo;
	
	
	@Test
	public void serviceTest() 
	{	
		System.out.println(" starting serviveTest");
		
		Iterable<Product> prods  = prodSvc.findAllProducts();
		
		   
		for(Product prod:prods)
		{
		   System.out.println( " Prod name " + prod.getName() + " description: " + prod.getDescription() + " price: " + prod.getPrice());
		}
	}
	
	
	@Test
	public void controllerTest()
	{
		System.out.println("starting controller test");
		
		String response = this.restTemplate.getForObject("/demo/getAllProduct",
				          String.class);
		
		System.out.println(" Response is " + response);
	}
	
	
	@Test
	public void repoTest()
	{
		System.out.println("starting repo test");
		
		Iterable<Product> prods  = prodRepo.findAll();
		
		for(Product prod:prods)
		{
		   System.out.println( " Prod name " + prod.getName() + " description: " + prod.getDescription() + " price: " + prod.getPrice());
		}
	}

	

}
