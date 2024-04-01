package com.nhnacademy.shoppingmall.repository;


import com.nhnacademy.shoppingmall.model.Cart;
import com.nhnacademy.shoppingmall.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();
    Order save(Order order);
}
