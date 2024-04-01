package com.nhnacademy.shoppingmall.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.shoppingmall.config.RootConfig;
import com.nhnacademy.shoppingmall.config.WebConfig;
import com.nhnacademy.shoppingmall.model.User;
import com.nhnacademy.shoppingmall.model.UserAddress;
import java.time.LocalDateTime;
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
class UserAddressRepositoryTest {
    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void test() {
        User user =
                new User("user2", "userName", "pwd", "20220222", "admin", 50, LocalDateTime.now(), LocalDateTime.now());
        userRepository.save(user);
        UserAddress userAddress = new UserAddress("광주 신안동 북구 ***빌", user);
        userAddressRepository.save(userAddress);

        int userAddressCount = userAddressRepository.countByAddressId(userAddress.getAddressId());
        assertThat(userAddressCount).isEqualTo(1);
    }

    @Test
    void findByUser_UserIdTest() {

    }
}