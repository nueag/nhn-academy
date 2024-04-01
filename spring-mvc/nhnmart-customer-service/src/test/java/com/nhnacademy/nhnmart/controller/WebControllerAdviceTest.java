package com.nhnacademy.nhnmart.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.nhnmart.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class WebControllerAdviceTest {
    private MockMvc mockMvc;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(WebControllerAdvice.class).build();
    }

    @Test
    @DisplayName("userId를 찾을 수 없는 경우: UserNotFoundException")
    void testGetUserThrowsUserNotFoundException() {
        when(userRepository.getUser(anyString())).thenReturn(null);

        assertThatThrownBy(
                () -> mockMvc.perform(get("/user/login/nonExistentUser"))
                        .andExpect(status().is4xxClientError())
                        .andExpect(view().name("thymeleaf/error")));

    }

    @Test
    @DisplayName("로그인 실패 테스트")
    public void testLoginFailure() {
        String userId = "testUser";
        String password = "incorrectPassword";
        when(userRepository.matches(userId, password)).thenReturn(false);

        assertThatThrownBy(
                () -> mockMvc.perform(post("/login")
                                .param("id", userId)
                                .param("pwd", password))
                        .andExpect(status().isNotFound())
                        .andExpect(redirectedUrl("/login")));
    }
}
