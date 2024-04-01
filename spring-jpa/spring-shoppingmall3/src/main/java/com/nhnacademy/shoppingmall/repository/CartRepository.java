package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.model.Cart;
import com.nhnacademy.shoppingmall.model.CartDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAll();

    Cart save(Cart cart);

    int countByProduct_ProductId(int productId);

    CartDto getCartByCartId(int cartId);

}
