package com.maxiflexy.ecommerce.customer.service;

import com.maxiflexy.ecommerce.customer.dto.CustomerRequest;
import com.maxiflexy.ecommerce.customer.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    String createCustomer(CustomerRequest request);
    void updateCustomer(CustomerRequest request);
    List<CustomerResponse> findAllCustomers();
    Boolean existsById(String customerId);
    CustomerResponse findById(String customerId);
    void deleteCustomer(String customerId);
}
