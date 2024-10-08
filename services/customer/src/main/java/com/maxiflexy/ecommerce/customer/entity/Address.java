package com.maxiflexy.ecommerce.customer.entity;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Validated
public class Address {

    private String street;
    private String houseNumber;
    private String zipCode;
}
