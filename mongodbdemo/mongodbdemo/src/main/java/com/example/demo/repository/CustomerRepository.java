package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.domain.Address;
import com.example.demo.domain.Customer;


public interface CustomerRepository extends MongoRepository<Customer, String>

{
   List<Customer> findByEmailAddress(String email);
   
   List<Customer> findByAddress(Address address);
   
}


