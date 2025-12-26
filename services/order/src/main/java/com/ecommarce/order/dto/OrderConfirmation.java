package com.ecommarce.order.dto;

import com.ecommarce.order.dto.customer.CustomerResponse;
import com.ecommarce.order.dto.product.PurchaseResponse;
import com.ecommarce.order.entity.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(String orderReference,
                                BigDecimal totalAmount,
                                PaymentMethod paymentMethod,
                                CustomerResponse customerResponse,
                                List<PurchaseResponse> purchaseRequest) {
}
