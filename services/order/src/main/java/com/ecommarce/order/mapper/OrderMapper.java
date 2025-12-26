package com.ecommarce.order.mapper;

import com.ecommarce.order.dto.OrderRequest;
import com.ecommarce.order.dto.OrderResponse;
import com.ecommarce.order.entity.Order;
import com.ecommarce.order.entity.PaymentMethod;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderMapper {

    public static Order getOrder(OrderRequest request) {
        return Order.builder().
                id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .paymentMethod(request.paymentMethod())
                .totalAmount(request.amount()).
                build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(order.getId(),
                order.getReference(),order.getTotalAmount(),order.getPaymentMethod(),order.getCustomerId());
    }
}
