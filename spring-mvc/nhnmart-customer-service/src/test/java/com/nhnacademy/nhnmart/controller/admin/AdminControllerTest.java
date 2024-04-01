package com.nhnacademy.nhnmart.controller.admin;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.nhnmart.domain.Inquiry;
import com.nhnacademy.nhnmart.domain.User;
import com.nhnacademy.nhnmart.exception.UserNotFoundException;
import com.nhnacademy.nhnmart.repository.InquiryRepository;
import com.nhnacademy.nhnmart.repository.UserRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class AdminControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Mock
    private InquiryRepository inquiryRepository;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        inquiryRepository = mock(InquiryRepository.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new AdminController(userRepository, inquiryRepository)).build();
    }

    @Test
    @DisplayName("admin 로그인 후 페이지 호출")
    public void testLoginUser() throws Exception {
        String userId = "admin";
        User admin = new User(userId, "12345", "admin", true);
        when(userRepository.getUser(anyString())).thenReturn(admin);
        when(inquiryRepository.getInquiriesByUnAnswered()).thenReturn(List.of(new Inquiry()));

        mockMvc.perform(get("/admin/login/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(view().name("thymeleaf/adminPage"))
                .andReturn();
    }

    @Test
    @DisplayName("아이디를 찾을 수 없는 경우 에러")
    public void testGetUserThrowsUserNotFoundException() {
        String nonExistentUserId = "nonExistentUser";
        when(userRepository.getUser(anyString())).thenReturn(null);
        Assertions.assertThatThrownBy(
                        () -> mockMvc.perform(get("/admin/login/{userId}", nonExistentUserId)))
                .hasCause(new UserNotFoundException());
    }
}