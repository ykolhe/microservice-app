package com.ecommarce.order.dto;

import com.ecommarce.order.dto.product.PurchaseRequest;
import com.ecommarce.order.entity.PaymentMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer should be Present")
        @NotEmpty(message = "Customer should be Present")
        @NotBlank(message = "Customer should be Present")
        String customerId,
        @NotEmpty(message = "you should be at least purchase one product")
        List<PurchaseRequest> products
) {

}
