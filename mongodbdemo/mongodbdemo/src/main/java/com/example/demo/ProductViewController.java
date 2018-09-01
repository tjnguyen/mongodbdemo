package com.example.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.ErrorInfo;
import com.example.demo.domain.Product;
import com.example.demo.exception.DataAccessException;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping(value="")
public class ProductViewController 
{
	 @Autowired
	 private ProductService prodService;

	   @GetMapping("/findproduct/{name}")
	   public String getProduct (@PathVariable("name")String name , Model uiModel) throws DataAccessException
	   {
		   Product product;
		   try
		   {
		      System.out.println("retrieve THE FIRST product ");
		      product = prodService.findProductByName(name);
		      uiModel.addAttribute("product", product);
		   }
		   catch(Exception ex)
		   {
			   throw new DataAccessException("Error retriving ONE product from database");
		   }
	   	
	       return "findproduct";
	   }  
	
	
	   @ExceptionHandler(DataAccessException.class)
	   public ModelAndView handleProductException (DataAccessException ex, HttpServletRequest req)
	   {
		   System.out.println("calling handleProductException");
		   req.setAttribute("javax.servlet.error.status_code",
							HttpStatus.INTERNAL_SERVER_ERROR.value()); 
		   req.setAttribute("exceptionMessage", ex.getMessage());
		   
		   ModelAndView model = new ModelAndView("error");
		   model.addObject("exception", ex);
		  
		   
		   return model;
		   

	   }
}
