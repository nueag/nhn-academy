package com.nhnacademy.shoppingmall.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.shoppingmall.config.RootConfig;
import com.nhnacademy.shoppingmall.config.WebConfig;
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

    @Test
    void test() {
        int userAddressCount = userAddressRepository.countByUserId("user");
        assertThat(userAddressCount).isEqualTo(1);
    }
}