package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ErrorInfo;
import com.example.demo.domain.Product;
import com.example.demo.domain.ProductResponse;
import com.example.demo.exception.DataAccessException;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping(value="/demo")
public class ProductController 
{
   
   @Autowired
   private ProductService prodService;
   
   @Autowired
   private CustomerService customerService;
   
  
   @GetMapping(value="/findAddressWithQuery")
   public String findAddressWithQuery(@RequestParam("street")String street, @RequestParam("zipcode")String zipcode)
   {
	   System.out.println("Enter findAddressWithQuery street" + street + "zip" + zipcode);
	   String addr = customerService.findAddressWithQuery(street, zipcode);
	  
	   return addr;
   }
   
   
   @GetMapping(value="/findByStreetAndZipcode")
   public String findByStreetAndZipcode(@RequestParam("street")String street, @RequestParam("zipcode")String zipcode)
   {
	   System.out.println("Enter findByStreetAndZipcode street" + street + "zip" + zipcode);
	   String addr = customerService.findByStreetAndZipcode(street, zipcode);
	  
	   return addr;
   }
   
   
   @GetMapping(value="/Hello/{name}", produces = "application/json")
   public String helloText(@PathVariable("name") String name)
   {
	   System.out.println("calling helloText");
	   ProductResponse resp = new ProductResponse();
	   
	   resp.setMessage("How are you " + name);
	   return "hello";
   }
   
   @GetMapping("/findProductByName/{name}")
   public Product getProductbyName (@PathVariable("name")String name) throws DataAccessException
   {
	   Product product;
	   try
	   {
	      product = prodService.findProductByName(name);
	   }
	   catch(Exception ex)
	   {
		   throw new DataAccessException("Error retriving ONE product by name from database");
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
	      products = prodService.findAllProducts();
	   }
	   catch(Exception ex)
	   {
		   throw new DataAccessException("Error retriving products from database");
	   }
    	
    	
    	return products;
    }  
   
   
   @RequestMapping(method=RequestMethod.GET, value="/getSpecificProducts")
   public Iterable<Product> getSpecificProducts () throws DataAccessException
   {
	   Iterable<Product> products  = null;
	   try
	   {
	      System.out.println("retrieve specific products");
	      products = prodService.findSpecificProducts();
	   }
	   catch(Exception ex)
	   {
		   throw new DataAccessException("Error retriving products from database");
	   }
   	
   	
   	return products;
   }  
 
   
   @RequestMapping(method=RequestMethod.POST, value="/saveProducts")
   public void saveProducts(@Valid List<Product> prods)
   {
	   prodService.persistProducts(prods);
	   
   }
   
   
   @RequestMapping(method=RequestMethod.GET, value="/displayerror")
   public String showError()
   {
	   return "displayerror";
   }
   
   @RequestMapping(method=RequestMethod.GET, value="/throwException")
   public void throwException ()
   {
	   throw new DataAccessException("Error accessing from database");
   }
   
   @ExceptionHandler(DataAccessException.class)
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @ResponseBody ErrorInfo
   handleProductException (DataAccessException ex, HttpServletRequest req)
   {
	   System.out.println("calling handleProductException");
	   req.setAttribute("javax.servlet.error.status_code",
						HttpStatus.INTERNAL_SERVER_ERROR.value()); 
	   req.setAttribute("exceptionMessage", ex.getMessage());
	   
	   ErrorInfo errorInfo = new ErrorInfo("/displayerror", ex);
	   
	   return errorInfo;
	   

   }
   
	   
}
