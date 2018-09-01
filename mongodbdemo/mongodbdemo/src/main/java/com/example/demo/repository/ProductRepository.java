package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>
{
	
	Page<Product> findByDescriptionContaining(String description, Pageable pageable);
	
	@Query("{ price: { $gt: 30 , $lte: 100 }}")
	List<Product>  getProductMoreExpensiveThan();
	
    
	Product findByName(String name);
	
	Optional<Product> findById(String id);
    
    

}