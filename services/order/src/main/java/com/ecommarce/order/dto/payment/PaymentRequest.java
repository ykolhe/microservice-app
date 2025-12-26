package com.ecommarce.order.dto.payment;

import com.ecommarce.order.dto.customer.CustomerResponse;
import com.ecommarce.order.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderID,
        String orderReference,
        CustomerResponse customer
) {
}
