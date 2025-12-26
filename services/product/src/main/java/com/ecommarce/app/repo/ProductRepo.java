package com.ecommarce.app.repo;

import com.ecommarce.app.entity.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {

    List<Product> findAllByIdInOrderById(List<Integer> productIds);
}
