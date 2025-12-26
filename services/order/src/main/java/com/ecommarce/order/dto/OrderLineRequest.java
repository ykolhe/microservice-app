package com.ecommarce.order.dto;

public record OrderLineRequest(
        Integer id,
        Integer orderID,
        Integer productId,
        double quantity) {
}
