package com.nhnacademy.nhnmart.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nhnacademy.nhnmart.domain.User;
import com.nhnacademy.nhnmart.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserRepositoryImplTest {

    private UserRepositoryImpl userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepositoryImpl();
    }

    @Test
    @DisplayName("존재하는 아이디인지 확인")
    public void testExists() {
        assertFalse(userRepository.exists("nonExistentId"));

        userRepository.addCustomer("testId", "testPassword", "Test User");
        assertTrue(userRepository.exists("testId"));
    }

    @Test
    @DisplayName("아이디와 비밀번호 확인")
    public void testMatches() {
        assertFalse(userRepository.matches("nonExistentId", "testPassword"));

        userRepository.addCustomer("testId", "passwordO", "Test User");
        assertTrue(userRepository.matches("testId", "passwordO"));
        assertFalse(userRepository.matches("testId", "passwordX"));
    }

    @Test
    @DisplayName("getUser 테스트")
    public void testGetUser() {
        userRepository.addCustomer("testId", "testPassword", "customer");
        User user = userRepository.getUser("testId");

        assertNotNull(user);
        assertEquals("testId", user.getUserId());
        assertEquals("customer", user.getName());
    }

    @Test
    @DisplayName("addCustomer 테스트: admin")
    public void testAddCustomer() {
        User user = userRepository.addCustomer("adminId", "pwd", "admin");

        assertEquals("adminId", user.getUserId());
        assertEquals("admin", user.getName());
        assertFalse(user.isAdmin());
    }

    @Test
    @DisplayName("addCustomer 테스트: customer")
    public void testAddAdmin() {
        User user = userRepository.addCustomer("customerId", "pwd", "customer");

        assertEquals("customerId", user.getUserId());
        assertEquals("customer", user.getName());
        assertFalse(user.isAdmin());
    }

    @Test
    @DisplayName("addCustomer 테스트: 사용자가 이미 있는 경우")
    public void testAddUserFail() {
        assertThrows(UserAlreadyExistsException.class, () ->
                userRepository.addCustomer("customerId", "pwd", "customer"));

    }
}