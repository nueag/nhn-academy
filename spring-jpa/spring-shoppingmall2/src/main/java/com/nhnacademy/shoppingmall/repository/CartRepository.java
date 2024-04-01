package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.model.Cart;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAll();
    Cart save(Cart cart);
    void update(Cart cart);
    int countByProductId(int productId);

    int deleteByProductId(int productId);
}
