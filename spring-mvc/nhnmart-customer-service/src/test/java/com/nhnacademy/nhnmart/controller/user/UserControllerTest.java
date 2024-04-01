package com.nhnacademy.nhnmart.controller.user;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.nhnmart.domain.Inquiry;
import com.nhnacademy.nhnmart.domain.User;
import com.nhnacademy.nhnmart.exception.UserNotFoundException;
import com.nhnacademy.nhnmart.repository.InquiryRepository;
import com.nhnacademy.nhnmart.repository.UserRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private InquiryRepository inquiryRepository;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @DisplayName("userId를 찾을 수 없는 경우: UserNotFoundException")
    void testGetUserThrowsUserNotFoundException() {
        when(userRepository.getUser(anyString())).thenReturn(null);

        assertThatThrownBy(
                () -> mockMvc.perform(get("/user/login/nonExistentUser")))
                .hasCause(new UserNotFoundException());

    }

    @Test
    @DisplayName("로그인 성공 후 사용자 페이지 테스트")
    void testLoginUser() throws Exception {
        List<Inquiry> inquiries = Arrays.asList(new Inquiry(), new Inquiry());
        when(userRepository.getUser(anyString())).thenReturn(new User());
        when(inquiryRepository.getInquiriesByUserId(anyString())).thenReturn(inquiries);

        mockMvc.perform(get("/user/login/user123"))
                .andExpect(status().isOk())
                .andExpect(view().name("thymeleaf/customerService"))
                .andExpect(model().attributeExists("user", "inquiries", "categories", "userId"));
    }
}
