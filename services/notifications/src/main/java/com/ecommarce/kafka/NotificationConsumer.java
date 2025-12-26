package com.ecommarce.kafka;

import com.ecommarce.email.EmailService;
import com.ecommarce.kafka.order.OrderConfirmation;
import com.ecommarce.kafka.payment.PaymentConfirmation;
import com.ecommarce.notification.Notification;
import com.ecommarce.notification.NotificationRepo;
import com.ecommarce.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepo notificationRepo;

    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic",groupId = "paymentGroup")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from payment-topic Topic :: %s",paymentConfirmation));
        notificationRepo.save(Notification.builder()
                .notificationType(NotificationType.PAYMENT_CONFIRMATION).notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation).build());
        var customerName = paymentConfirmation.getCustomerFirstName()+" "+paymentConfirmation.getCustomerLastName();
        emailService.paymentSuccessEmail(paymentConfirmation.getCustomerEmail(),
                customerName,paymentConfirmation.getAmount(),paymentConfirmation.getOrderReference());
    }

    @KafkaListener(topics = "order-topic",groupId = "orderGroup")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from order-topic Topic :: %s",orderConfirmation));
        notificationRepo.save(Notification.builder()
                .notificationType(NotificationType.ORDER_CONFIRMATION).notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation).build());
        var customerName = orderConfirmation.getCustomer().firstName()+" "+orderConfirmation.getCustomer().lastName();
        emailService.paymentSuccessEmail(orderConfirmation.getCustomer().email(),
                customerName,orderConfirmation.getAmount(),orderConfirmation.getReference());

    }

}
