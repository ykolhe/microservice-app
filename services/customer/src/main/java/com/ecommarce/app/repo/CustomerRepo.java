package com.ecommarce.app.repo;

import com.ecommarce.app.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepo extends MongoRepository<Customer,String> {

}
