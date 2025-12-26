package com.ecommarce.payment.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "first Name is required")
        String firstName,
        @NotNull(message = "last Name is required")
        String lastName,
        @NotNull(message = "Email is required")
        @Email(message = "The customer email id is not valid")
        String email



) {
}
