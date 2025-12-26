package com.ecommarce.email;

import com.ecommarce.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;

    private final SpringTemplateEngine springTemplateEngine;

    @Async
    public void paymentSuccessEmail(String destinationEmail, String customerName,
                                    BigDecimal amount,String orderReference) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message
        ,MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
        mimeMessageHelper.setFrom("ykolhe2910@gmail.com");
        final String templateName=EmailTemplate.PAYMENT_CONFIRMATION.getTemplate();
        Map<String ,Object> variable = new HashMap<>();
        variable.put("customerName",customerName);
        variable.put("amount",amount);
        variable.put("orderReference",orderReference);
        Context context = new Context();
        context.setVariables(variable);
        mimeMessageHelper.setSubject(EmailTemplate.PAYMENT_CONFIRMATION.getSubject());
        try{
            String htmlTemplate = springTemplateEngine.process(templateName,context);
            mimeMessageHelper.setText(htmlTemplate,true);
            mimeMessageHelper.setTo(destinationEmail);
            javaMailSender.send(message);
            log.info(String.format("INFO - Email Successfully send to %s with template %s , ",destinationEmail,templateName));
        }catch (Exception exception){
            log.warn("WARNING - Cannot send email to {}",destinationEmail);
        }
    }

    @Async
    public void sendOrderConfirmationEmail(String destinationEmail, String customerName,
                                           BigDecimal amount, String orderReference,
                                           List<Product> products) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message
                ,MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
        mimeMessageHelper.setFrom("ykolhe2910@gmail.com");
        final String templateName=EmailTemplate.ORDER_CONFIRMATION.getTemplate();
        Map<String ,Object> variable = new HashMap<>();
        variable.put("customerName",customerName);
        variable.put("totalAmount",amount);
        variable.put("orderReference",orderReference);
        variable.put("products",products);
        Context context = new Context();
        context.setVariables(variable);
        mimeMessageHelper.setSubject(EmailTemplate.ORDER_CONFIRMATION.getSubject());
        try{
            String htmlTemplate = springTemplateEngine.process(templateName,context);
            mimeMessageHelper.setText(htmlTemplate,true);
            mimeMessageHelper.setTo(destinationEmail);
            javaMailSender.send(message);
            log.info(String.format("INFO - Email Successfully send to %s with template %s , ",destinationEmail,templateName));
        }catch (Exception exception){
            log.warn("WARNING - Cannot send email to {}",destinationEmail);
        }
    }

}
