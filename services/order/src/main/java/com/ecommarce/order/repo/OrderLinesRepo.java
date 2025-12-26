package com.ecommarce.order.repo;

import com.ecommarce.order.entity.OrderLines;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderLinesRepo extends JpaRepository<OrderLines,Integer> {

    List<OrderLines> findAllByOrderId(Integer orderId);

}
