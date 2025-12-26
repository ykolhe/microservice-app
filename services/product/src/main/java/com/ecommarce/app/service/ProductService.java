package com.ecommarce.app.service;

import com.ecommarce.app.dto.ProductPurchaseRequest;
import com.ecommarce.app.dto.ProductPurchaseResponse;
import com.ecommarce.app.dto.ProductRequest;
import com.ecommarce.app.dto.ProductResponse;
import com.ecommarce.app.entity.Product;
import com.ecommarce.app.exception.ProductNotFoundException;
import com.ecommarce.app.exception.ProductPurchaseException;
import com.ecommarce.app.mapper.ProductMapper;
import com.ecommarce.app.repo.ProductRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    public Integer createProduct(@Valid ProductRequest productRequest) {
        var product = productMapper.toProduct(productRequest);
        Product prd = productRepo.save(product);
        return prd.getId();
    }

    public List<ProductPurchaseResponse> purchaseProduct(@Valid List<ProductPurchaseRequest> productPurchaseRequest) {
        var productIds = productPurchaseRequest.stream().map(ProductPurchaseRequest::productId).toList();
        var storedProduct = productRepo.findAllByIdInOrderById(productIds);
        if(storedProduct.size() != productIds.size()){
            throw new ProductPurchaseException("One or more Product does not exists");
        }
        var storeRequest = productPurchaseRequest.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchaseProducts = new ArrayList<ProductPurchaseResponse>();
        for(int i=0;i<storedProduct.size();i++) {
            var product = storedProduct.get(i);
            var productRequest = storeRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("In sufficient stock quantity for product with ID ::" + productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepo.save(product);
            purchaseProducts.add(productMapper.toProductPurchaseResponse(product,productRequest.quantity()));
        }
        return purchaseProducts;
    }

    public ProductResponse findByProduct(Integer productId) {
        return productRepo.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(()-> new ProductNotFoundException("No Product found for the give Id"+productId));
    }

    public List<ProductResponse> findAll() {
        return productRepo.findAll()
                .stream().map(productMapper::toProductResponse)
                .toList();
    }
}
