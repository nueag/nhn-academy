package com.nhnacademy.nhnmart.controller.user;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.nhnmart.domain.Category;
import com.nhnacademy.nhnmart.domain.Inquiry;
import com.nhnacademy.nhnmart.domain.User;
import com.nhnacademy.nhnmart.repository.InquiryRepository;
import com.nhnacademy.nhnmart.repository.UserRepository;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class UserInquiryControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private InquiryRepository inquiryRepository;

    @InjectMocks
    private UserInquiryController userInquiryController;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userInquiryController).build();
    }

    @Test
    @DisplayName("문의하기 화면을 출력")
    public void testGetInquiry() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", "customer");

        mockMvc.perform(get("/user/inquiry")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("inquiry"))
                .andExpect(view().name("thymeleaf/userInquiry"));

    }

    @Test
    @DisplayName("문의하기 게시물 등록 테스트: 일반 파일")
    public void testPostInquiry() throws Exception {
        MockHttpSession session = new MockHttpSession();
        String userId = "customer";
        session.setAttribute("userId", userId);

        when(userRepository.getUser(anyString())).thenReturn(new User()); // Stubbing for user retrieval
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Hello, World!".getBytes());

        mockMvc.perform(multipart("/user/inquiry")
                        .file(file)
                        .param("title", "Test Title")
                        .param("category", String.valueOf(Category.OTHER_INQUIRY))
                        .param("content", "Test Content")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/login/" + userId));

    }

    @Test
    @DisplayName("문의하기 게시물 등록 테스트: 이미지 파일")
    public void testImagePostInquiry() throws Exception {
        MockHttpSession session = new MockHttpSession();
        String userId = "customer";
        session.setAttribute("userId", userId);

        when(userRepository.getUser(anyString())).thenReturn(new User()); // Stubbing for user retrieval
        MockMultipartFile file =
                new MockMultipartFile("file", "test-image.jpeg", "image/jpeg", "Test Image Content".getBytes());

        mockMvc.perform(multipart("/user/inquiry")
                        .file(file)
                        .param("title", "Test Title")
                        .param("category", String.valueOf(Category.OTHER_INQUIRY))
                        .param("content", "Test Content")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/login/" + userId));

    }

    @Test
    @DisplayName("문의 세부 내역 확인 테스트")
    public void testGetDetail() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", "customer");
        when(inquiryRepository.getInquiry(anyString(), anyLong())).thenReturn(new Inquiry());
        when(userRepository.getUser(anyString())).thenReturn(new User());

        mockMvc.perform(get("/user/inquiry/detail/{inquiryId}", 1L).session(session))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("inquiry", "user"))
                .andExpect(view().name("thymeleaf/inquiryDetail"));

    }

    @Test
    @DisplayName("카테고리별 리스트 조회 테스트: 카테고리 선택")
    public void testGetListByCategory() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", "customer");
        when(inquiryRepository.getInquiriesByCategory(anyString())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/user/inquiry/category")
                        .param("category", "category")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(model().attribute("inquiries", Collections.emptyList()))
                .andExpect(model().attribute("categories", Category.getCategoryList()))
                .andExpect(view().name("thymeleaf/customerService"));

    }

    @Test
    @DisplayName("카테고리별 리스트 조회 테스트: 전체 선택")
    public void testGetListByAllCategory() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", "customer");
        when(inquiryRepository.getInquiriesByCategory(anyString())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/user/inquiry/category")
                        .param("category", "전체")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/login/" + "customer"));

    }
}
