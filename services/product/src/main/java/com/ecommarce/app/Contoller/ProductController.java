package com.ecommarce.app.Contoller;

import com.ecommarce.app.dto.ProductPurchaseRequest;
import com.ecommarce.app.dto.ProductPurchaseResponse;
import com.ecommarce.app.dto.ProductRequest;
import com.ecommarce.app.dto.ProductResponse;
import com.ecommarce.app.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest productRequest){
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @GetMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> productPurchaseResponse(
            @RequestBody @Valid List<ProductPurchaseRequest> productPurchaseRequest
    ){
        return ResponseEntity.ok(productService.purchaseProduct(productPurchaseRequest));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable("product-id") Integer productId){
        return ResponseEntity.ok(productService.findByProduct(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

}
