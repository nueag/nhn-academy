package com.nhnacademy.nhnmart.controller.admin;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.nhnmart.domain.Inquiry;
import com.nhnacademy.nhnmart.domain.User;
import com.nhnacademy.nhnmart.repository.InquiryRepository;
import com.nhnacademy.nhnmart.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


class AdminInquiryControllerTest {
    private MockMvc mockMvc;

    @Mock
    private InquiryRepository inquiryRepository;

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private AdminInquiryController adminInquiryController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminInquiryController).build();
    }

    @Test
    @DisplayName("문의 세부 정보 페이지 호출 테스트")
    void testGetDetail() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", "admin123");

        when(userRepository.getUser(anyString())).thenReturn(new User());
        when(inquiryRepository.getInquiry(anyString(), anyLong())).thenReturn(new Inquiry());

        mockMvc.perform(get("/admin/inquiry/detail/customer123/1")
                        .session(session)
                        .param("customerId", "customer")
                        .param("inquiryId", "inquiryId"))
                .andExpect(status().isOk())
                .andExpect(view().name("thymeleaf/inquiryDetail"))
                .andExpect(model().attributeExists("inquiry", "user"));
    }

    @Test
    @DisplayName("답변 작성 페이지 호출")
    void answerPage() throws Exception {
        when(inquiryRepository.getInquiry(anyString(), anyLong())).thenReturn(new Inquiry());

        mockMvc.perform(get("/admin/inquiry/answer/customer123/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("thymeleaf/answerPage"))
                .andExpect(model().attributeExists("inquiry"));
    }

    @Test
    @DisplayName("답변 등록 완료 페이지 호출")
    void answerCompleted() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", "admin");

        mockMvc.perform(post("/admin/inquiry/answer/admin/1")
                        .param("answerText", "Test answer")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/login/admin"));
    }


}