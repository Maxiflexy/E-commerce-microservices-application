package com.maxiflexy.ecommerce.customer.dto;

import com.maxiflexy.ecommerce.customer.entity.Address;

public record CustomerResponse(

        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
