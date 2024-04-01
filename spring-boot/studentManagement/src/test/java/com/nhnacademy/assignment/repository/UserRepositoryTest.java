package com.nhnacademy.assignment.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.assignment.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void test() {
        User user = new User("123", "456", 20, "gaeun");
        userRepository.save(user);
        Assertions.assertThat(userRepository.getPassword(user.getId())).isEqualTo(user.getPassword());
    }

}