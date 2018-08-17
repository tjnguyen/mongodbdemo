package com.example.springbootaws.reposistory;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootaws.domain.Product;

@EnableScan
public interface ProductRepository extends CrudRepository<Product, String>
{
	Product findByName(String name);
	List<Product> findAll();
}
