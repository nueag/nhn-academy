package com.nhnacademy.nhnmart.controller.login;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.nhnmart.domain.User;
import com.nhnacademy.nhnmart.repository.UserRepository;
import javax.security.auth.login.FailedLoginException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class LoginControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new LoginController(userRepository)).build();
    }

    @Test
    @DisplayName("로그인 초기 화면 테스트")
    public void testLoginForm() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("thymeleaf/loginForm"));
    }

    @Test
    @DisplayName("로그인 성공 테스트: 관리자")
    public void testAdminLoginSuccess() throws Exception {
        String userId = "testUser";
        String password = "testPassword";
        when(userRepository.matches(userId, password)).thenReturn(true);
        when(userRepository.getUser(userId)).thenReturn(new User(userId, password, "name", true));

        mockMvc.perform(post("/login")
                        .param("id", userId)
                        .param("pwd", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/login/" + userId));
    }

    @Test
    @DisplayName("로그인 성공 테스트: 고객")
    public void testCustomerLoginSuccess() throws Exception {
        String userId = "testUser";
        String password = "incorrectPassword";

        when(userRepository.matches(userId, password)).thenReturn(true);
        when(userRepository.getUser(userId)).thenReturn(new User(userId, password, "name", false));

        mockMvc.perform(post("/login")
                        .param("id", userId)
                        .param("pwd", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/login/" + userId));
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
        ).hasCause(new FailedLoginException());
    }

    @Test
    @DisplayName("사용자 페이지 리다이렉션: 관리자")
    public void testDoPageAdmin() throws Exception {
        String userId = "admin";
        when(userRepository.getUser(userId)).thenReturn(new User(userId, "pass", "admin", true));

        mockMvc.perform(MockMvcRequestBuilders.get("/login/{userId}", userId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/login/" + userId));
    }

    @Test
    @DisplayName("사용자 페이지 리다이렉션: 고객")
    public void testDoPageRedirectForNonAdmin() throws Exception {
        String userId = "customer";
        when(userRepository.getUser(userId)).thenReturn(new User(userId, "pass", "customer", false));

        mockMvc.perform(MockMvcRequestBuilders.get("/login/{userId}", userId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/login/" + userId));
    }
}
