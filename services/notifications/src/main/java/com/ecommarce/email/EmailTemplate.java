package com.ecommarce.email;

import lombok.Getter;

public enum EmailTemplate {
    PAYMENT_CONFIRMATION("payment-confimation.html","Payment Successfully proceeed"),
    ORDER_CONFIRMATION("order-confimation.html","Order Successfully proceeed");

    @Getter
    private final String template;

    @Getter
    private final String subject;

    EmailTemplate(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
