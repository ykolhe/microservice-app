package com.ecommarce.kafka.order;

import com.ecommarce.kafka.payment.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderConfirmation {
    private String reference;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private Customer customer;
    private List<Product> products;
}
