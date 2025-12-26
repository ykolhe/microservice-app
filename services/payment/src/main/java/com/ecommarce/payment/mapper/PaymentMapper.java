package com.ecommarce.payment.mapper;

import com.ecommarce.payment.dto.PaymentRequest;
import com.ecommarce.payment.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toEntity(PaymentRequest paymentRequest) {
        return Payment.builder().
                id(paymentRequest.id()).
                amount(paymentRequest.amount()).
                paymentMethod(paymentRequest.paymentMethod()).
                orderId(paymentRequest.orderID()).build();
    }
}
