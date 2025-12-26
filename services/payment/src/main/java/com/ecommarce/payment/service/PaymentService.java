package com.ecommarce.payment.service;

import com.ecommarce.payment.dto.PaymentNotificationRequest;
import com.ecommarce.payment.dto.PaymentRequest;
import com.ecommarce.payment.mapper.PaymentMapper;
import com.ecommarce.payment.notification.NotificationProducer;
import com.ecommarce.payment.repo.PaymentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepo paymentRepo;

    private final PaymentMapper paymentMapper;

    private final NotificationProducer notificationProducer;

    public Integer doPayment(PaymentRequest paymentRequest) {
        var payment = paymentRepo.save(paymentMapper.toEntity(paymentRequest));

        notificationProducer.sendNotification(new PaymentNotificationRequest(
                paymentRequest.orderReference(),
                paymentRequest.amount(),
                paymentRequest.paymentMethod(),
                paymentRequest.customer().firstName(),
                paymentRequest.customer().lastName(),
                paymentRequest.customer().email()
        ));
        return payment.getId();
    }
}
