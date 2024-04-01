package com.nhnacademy.shoppingmall.repository;


import com.nhnacademy.shoppingmall.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends OrderRepositoryCustom, JpaRepository<Order, Integer> {
    List<Order> findAll();
}
