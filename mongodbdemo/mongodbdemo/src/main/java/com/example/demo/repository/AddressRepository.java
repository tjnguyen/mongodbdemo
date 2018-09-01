package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.domain.Address;


public interface AddressRepository extends MongoRepository<Address, String>

{
   @Query("{'zipcode': ?0 , 'street': ?1}")
   Address  findAddressWithQuery(String zipcode, String street);
	
   Address  findByStreetAndZipcode(String street, String zipcode);
     
}


