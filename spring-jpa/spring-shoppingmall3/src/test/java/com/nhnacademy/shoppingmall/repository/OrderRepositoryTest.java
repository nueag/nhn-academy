package com.nhnacademy.shoppingmall.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.shoppingmall.config.RootConfig;
import com.nhnacademy.shoppingmall.config.WebConfig;
import com.nhnacademy.shoppingmall.model.Cart;
import com.nhnacademy.shoppingmall.model.Order;
import com.nhnacademy.shoppingmall.model.Product;
import com.nhnacademy.shoppingmall.model.User;
import java.time.LocalDateTime;
import java.util.List;
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
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    void test() {
        User user =
                new User("user2", "userName", "pwd", "20220222", "admin", 50, LocalDateTime.now(), LocalDateTime.now());
        userRepository.save(user);
        Product product = new Product("아보카도", 3000, 300, null, null, null, null);
        productRepository.save(product);
        Cart cart = new Cart(user, product, 3);
        cartRepository.save(cart);
        List<Cart> cartList = List.of(cart);
        Order order = new Order(1, null, null, user, cartList);
        orderRepository.save(order);
        orderRepository.flush();
        List<Order> orders = orderRepository.findAll();
        assertThat(orders.size()).isEqualTo(1);
    }

}
