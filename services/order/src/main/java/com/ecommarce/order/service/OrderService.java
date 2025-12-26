package com.ecommarce.order.service;

import com.ecommarce.order.customer.CustomerClient;
import com.ecommarce.order.dto.OrderConfirmation;
import com.ecommarce.order.dto.OrderLineRequest;
import com.ecommarce.order.dto.OrderRequest;
import com.ecommarce.order.dto.OrderResponse;
import com.ecommarce.order.dto.payment.PaymentRequest;
import com.ecommarce.order.dto.product.ProductClient;
import com.ecommarce.order.dto.product.PurchaseRequest;
import com.ecommarce.order.dto.product.PurchaseResponse;
import com.ecommarce.order.exception.BusinessException;
import com.ecommarce.order.kafka.OrderProducer;
import com.ecommarce.order.mapper.OrderMapper;
import com.ecommarce.order.payment.PaymentClient;
import com.ecommarce.order.repo.OrderRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;

    private final ProductClient productClient;

    private final OrderRepo orderRepo;

    private final OrderMapper orderMapper;

    private final OrderLineService orderLineService;

    private final OrderProducer orderProducer;

    private final PaymentClient paymentClient;

    public Integer createOrder(@Valid OrderRequest request) {
        // Check customer
        var customer = this.customerClient.findByCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Can not create Order :: No Customer exists"));
        // purchase the products
        List<PurchaseResponse> purchaseResponses = productClient.purchaseProduct(request.products());
        // persist order
        var order = orderRepo.save(OrderMapper.getOrder(request));

        // persist order line

        for (PurchaseRequest purchaseRequest : request.products()) {
            Integer orderLineId = orderLineService.saveOrderLine(new OrderLineRequest(null,
                    order.getId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity())
            );
        }

        // start payment process

        // send the order confirmation
        var paymentRequest = new PaymentRequest(request.amount(),request.paymentMethod(),order.getId(),order.getReference()
                ,customer);
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                request.reference(), request.amount()
                , request.paymentMethod(), customer, purchaseResponses
        ));
        return order.getId();
    }

    public List<OrderResponse> getOrders() {
        return orderRepo.findAll().stream()
                .map(orderMapper::fromOrder).toList();
    }

    public OrderResponse getOrder(Integer orderId) {
        return orderRepo.findById(orderId).map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with provided id"+orderId)));
    }
}
