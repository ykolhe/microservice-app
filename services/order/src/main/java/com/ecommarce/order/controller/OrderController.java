package com.ecommarce.order.controller;

import com.ecommarce.order.dto.OrderRequest;
import com.ecommarce.order.dto.OrderResponse;
import com.ecommarce.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest orderRequest){
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(){
        return ResponseEntity.ok(orderService.getOrders());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable("order-id") Integer orderId){
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

}
