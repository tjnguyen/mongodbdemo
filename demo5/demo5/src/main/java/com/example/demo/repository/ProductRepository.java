package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Product;


public interface ProductRepository extends CrudRepository<Product, Integer>
{
	
	Page<Product> findByDescriptionContaining(String description, Pageable pageable);
	
    
	Product findByName(String name);
	
	Optional<Product> findById(String id);
    
    

}
