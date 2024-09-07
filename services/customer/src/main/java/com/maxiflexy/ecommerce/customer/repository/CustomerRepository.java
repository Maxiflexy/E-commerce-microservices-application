package com.maxiflexy.ecommerce.customer.repository;

import com.maxiflexy.ecommerce.customer.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
