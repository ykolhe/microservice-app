package com.ecommarce.app.dto;


import com.ecommarce.app.entity.Address;

public record CustomerResponse(String id,
                               String firstName,
                               String lastName,
                               String email,
                               Address address) {

}
