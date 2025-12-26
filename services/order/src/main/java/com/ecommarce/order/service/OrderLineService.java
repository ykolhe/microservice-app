package com.ecommarce.order.service;

import com.ecommarce.order.dto.OrderLineRequest;
import com.ecommarce.order.dto.OrderLineResponse;
import com.ecommarce.order.exception.BusinessException;
import com.ecommarce.order.mapper.OrderlinesMapper;
import com.ecommarce.order.repo.OrderLinesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLinesRepo orderLinesRepo;
    private final OrderlinesMapper orderlinesMapper;

    public Integer saveOrderLine(OrderLineRequest orderLines) {
        var order =orderlinesMapper.toOrderLine(orderLines);
        return orderLinesRepo.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLinesRepo.findAllByOrderId(orderId)
                .stream().map(orderlinesMapper::toOrderLineDto).toList();
    }
}
