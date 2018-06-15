package resources;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import domain.ErrorInfo;
import domain.Product;
import domain.ProductResponse;
import exception.DataAccessException;

@RestController
@RequestMapping(value="/demo")
public class ProductController 
{
   
   @Autowired
   private ProductService prodService;
   
   @Autowired
   private ProductValidator prodValidator;
   
   
   
   @InitBinder("product") 
   public void setupBinder(WebDataBinder binder) 
   { 
      binder.addValidators(prodValidator); 
   } 

   @RequestMapping(method=RequestMethod.GET, value="/Hello/{name}", produces = "application/json")
   public ProductResponse helloText(@PathVariable("name") String name)
   {
	   System.out.println("calling helloText");
	   ProductResponse resp = new ProductResponse();
	   
	   resp.setMessage("Hello " + name + "!  How are you? " );
	   return resp;
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
   
   
   
   
   @RequestMapping(method=RequestMethod.POST, value="/saveProducts")
   public void saveProducts(@Valid List<Product> prods)
   {
	   prodService.persistProducts(prods);
	   
   }
   
   @RequestMapping(method=RequestMethod.PUT, value="/modifyProduct/{id}")
   public void modifyProduct(@Valid Product prod, @PathVariable("id") int id)
   {
	   prodService.modifyProduct(prod, id);
	   
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
