package com.ecommarce.app.dto;

import com.ecommarce.app.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(String id,
         @NotNull(message = "Customer firstName required")
         String firstName,
         @NotNull(message = "Customer lastname required")
         String lastName,
         @NotNull(message = "Customer email required")
         @Email(message = "Customer email is not valid email")
         String email,
         Address address
) {
}
