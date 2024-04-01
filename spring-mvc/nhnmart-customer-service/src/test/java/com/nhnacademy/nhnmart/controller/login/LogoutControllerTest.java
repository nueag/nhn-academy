package com.nhnacademy.nhnmart.controller.login;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class LogoutControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new LogoutController()).build();
    }

    @Test
    @DisplayName("로그아웃 테스트")
    public void testLogout() throws Exception {
        HttpSession session = mockMvc.perform(get("/"))
                .andReturn()
                .getRequest()
                .getSession();
        session.setAttribute("COOKIE", "someCookieValue");

        mockMvc.perform(get("/logout").session((MockHttpSession) session))
                .andExpect(status().isOk())
                .andExpect(view().name("thymeleaf/loginForm"));

        Object cookieAttribute = session.getAttribute("COOKIE");
        Assertions.assertEquals(cookieAttribute, null);
    }

}