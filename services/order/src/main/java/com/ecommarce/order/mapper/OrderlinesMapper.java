package com.ecommarce.order.mapper;

import com.ecommarce.order.dto.OrderLineRequest;
import com.ecommarce.order.dto.OrderLineResponse;
import com.ecommarce.order.entity.Order;
import com.ecommarce.order.entity.OrderLines;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderlinesMapper {

    public OrderLines toOrderLine(OrderLineRequest orderLines) {
        return OrderLines.builder().id(orderLines.id())
                .quantity(orderLines.quantity())
                .order(Order.builder()
                        .id(orderLines.orderID())
                        .build()).
                productId(orderLines.productId())
        .build();
    }

    public OrderLineResponse toOrderLineDto(OrderLines orderLines) {
        return new OrderLineResponse(orderLines.getId(),orderLines.getQuantity());
    }
}
