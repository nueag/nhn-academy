package com.nhnacademy.shoppingmall.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.shoppingmall.config.RootConfig;
import com.nhnacademy.shoppingmall.config.WebConfig;
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
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void findAllByUserIdTest() {
        List<User> users = userRepository.findAllByUserId("user");
        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    void saveTest() {
        User user =
                new User("user2", "userName", "pwd", "20220222", "admin", 50, LocalDateTime.now(), LocalDateTime.now());
        User testUser = userRepository.saveAndFlush(user);
        assertThat(testUser.getUserId()).isEqualTo("user2");
    }
}