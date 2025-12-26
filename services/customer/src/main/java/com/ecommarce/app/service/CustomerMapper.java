package com.ecommarce.app.service;

import com.ecommarce.app.dto.CustomerRequest;
import com.ecommarce.app.dto.CustomerResponse;
import com.ecommarce.app.entity.Address;
import com.ecommarce.app.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toEntity(CustomerRequest customer) {
        if (customer == null)
            return null;
        return Customer.builder().id(customer.id())
                .firstName(customer.firstName())
                .lastName(customer.lastName())
                .email(customer.email())
                .address(customer.address())
                .build();
    }

    public CustomerResponse toDto(Customer customer) {
        return new CustomerResponse(customer.getId(),
                customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getAddress());
    }
}
