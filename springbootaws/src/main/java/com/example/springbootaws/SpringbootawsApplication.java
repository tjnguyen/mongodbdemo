package com.example.springbootaws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.example.springbootaws.domain.Product;
import com.example.springbootaws.reposistory.ProductRepository;

@SpringBootApplication
public class SpringbootawsApplication   implements CommandLineRunner
{
	private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;
    
    @Autowired
    private ProductRepository  productRepo;
    
    
	public static void main(String[] args) {
		SpringApplication.run(SpringbootawsApplication.class, args);
	}
	
	
	@Override
    public void run(String... args) throws Exception 
    {
		try
		{
		      dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

		      CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Product.class);

		      tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

		      amazonDynamoDB.createTable(tableRequest);
		      
		      Product product1 = new Product("product1", "for sale", 12);
		      Product product2 = new Product("product2", "for sale", 14);
		      
		      productRepo.save(product1);
		      productRepo.save(product2);
		      
		      
		       
		}
		catch(Exception ex)
		{
			 ex.printStackTrace();
			 System.out.println(" catch exception " + ex.getMessage());
		}
    }
}
