package com.ecommarce.app.mapper;

import com.ecommarce.app.dto.ProductPurchaseResponse;
import com.ecommarce.app.dto.ProductRequest;
import com.ecommarce.app.dto.ProductResponse;
import com.ecommarce.app.entity.Category;
import com.ecommarce.app.entity.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(@Valid ProductRequest productRequest) {
        return Product.builder().
                name(productRequest.name())
                .name(productRequest.name())
                .description(productRequest.description())
                .availableQuantity(productRequest.availableQuantity())
                .category(Category.builder().
                        id(productRequest.categoryID()).
                        build()).
                build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(product.getId(),product.getName()
                ,product.getDescription(),product.getAvailableQuantity(),
        product.getPrice(),product.getCategory().getId(),product.getName(), product.getDescription());
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice(),quantity);
    }
}
