    package com.ecommarce.notification;

    import com.ecommarce.kafka.order.OrderConfirmation;
    import com.ecommarce.kafka.payment.PaymentConfirmation;
    import lombok.*;
    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.mapping.Document;

    import java.time.LocalDateTime;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Document
    public class Notification {

        @Id
        private String id;
        private NotificationType notificationType;
        private LocalDateTime notificationDate;
        private OrderConfirmation orderConfirmation;
        private PaymentConfirmation paymentConfirmation;
    }
