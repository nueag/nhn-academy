package com.nhnacademy.shoppingmall.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.shoppingmall.config.RootConfig;
import com.nhnacademy.shoppingmall.config.WebConfig;
import com.nhnacademy.shoppingmall.model.Cart;
import com.nhnacademy.shoppingmall.model.CartDto;
import com.nhnacademy.shoppingmall.model.Product;
import com.nhnacademy.shoppingmall.model.User;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findAllTest() {
        List<Cart> carts = cartRepository.findAll();
        assertThat(carts.size()).isEqualTo(2);
    }

    @Test
    void saveCartTest() {
        User user = new User("user2", "userName", "pwd", "20220222", "admin", 50, LocalDateTime.now(), LocalDateTime.now());
        userRepository.save(user);
        Product product = new Product("아보카도", 3000, 300, null, null, null, null);
        productRepository.save(product);
        Cart cart = new Cart(user, product, 3);
        Cart saveCart = cartRepository.save(cart);
        cartRepository.flush();

        CartDto cartDto = cartRepository.getCartByCartId(saveCart.getCartId());
        assertThat(cartDto.getProduct().getProductName()).isEqualTo(saveCart.getProduct().getProductName());
    }



}