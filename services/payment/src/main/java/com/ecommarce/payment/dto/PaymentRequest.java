package com.ecommarce.payment.dto;

import com.ecommarce.payment.dto.customer.Customer;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderID,
        String orderReference,
        Customer customer
) {
}
