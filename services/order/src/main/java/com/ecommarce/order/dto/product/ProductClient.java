package com.ecommarce.order.dto.product;

import com.ecommarce.order.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;

    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProduct(List<PurchaseRequest> requests){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<List<PurchaseRequest>> purchaseRequest = new HttpEntity<>(requests,httpHeaders);
        ParameterizedTypeReference<List<PurchaseResponse>> typeReference = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<PurchaseResponse>> response = restTemplate.exchange(productUrl + "/purchase",
                HttpMethod.POST,
                purchaseRequest,
                typeReference);
        if(response.getStatusCode().isError()){
            throw new BusinessException("An error occurred while processing the product purchase "+response.getStatusCode());
        }
        return response.getBody();

    }



}
