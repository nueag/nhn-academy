package com.nhnacademy.nhnmart.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.nhnmart.domain.Category;
import com.nhnacademy.nhnmart.domain.Inquiry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class InquiryRepositoryImplTest {

    @InjectMocks
    private InquiryRepositoryImpl inquiryRepository;

    @Mock
    private Map<String, List<Inquiry>> inquiryMapMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("유저 아이디로 문의 목록 가져오기")
    public void testGetInquiriesByUserId() {
        Inquiry inquiry = new Inquiry("customerId", "환불해주세요", Category.REFUND_EXCHANGE, "그냥해주세요", null);
        List<Inquiry> inquiryList = new ArrayList<>();
        inquiryList.add(inquiry);

        when(inquiryMapMock.containsKey("customerId")).thenReturn(true);
        when(inquiryMapMock.get("customerId")).thenReturn(inquiryList);

        List<Inquiry> result = inquiryRepository.getInquiriesByUserId("customerId");

        assertEquals(1, result.size());
        assertEquals("customerId", result.get(0).getCustomerId());
    }


    @Test
    @DisplayName("답변 안 된 문의 목록 가져오기")
    public void testGetInquiriesByUnAnswered() {
        Inquiry answeredInquiry = new Inquiry("customerId", "환불해주세요", Category.REFUND_EXCHANGE, "그냥해주세요", null);
        List<Inquiry> inquiryList = new ArrayList<>();
        inquiryList.add(answeredInquiry);

        when(inquiryMapMock.values()).thenReturn(List.of(inquiryList));

        List<Inquiry> result = inquiryRepository.getInquiriesByUnAnswered();
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("특정 문의 가져오기")
    public void testGetInquiry() {
        Inquiry inquiry = new Inquiry("customerId", "환불해주세요", Category.REFUND_EXCHANGE, "그냥해주세요", null);
        List<Inquiry> inquiryList = new ArrayList<>();
        inquiryList.add(inquiry);

        when(inquiryMapMock.containsKey("customerId")).thenReturn(true);
        when(inquiryMapMock.get("customerId")).thenReturn(inquiryList);

        Inquiry result = inquiryRepository.getInquiry("customerId", 1L);

        assertNotNull(result);
        assertEquals("그냥해주세요", result.getContent());
    }

    @Test
    @DisplayName("문의 등록하기")
    public void testPostInquiry() {
        Inquiry inquiry = new Inquiry("customerId", "환불해주세요", Category.REFUND_EXCHANGE, "그냥해주세요", null);
        when(inquiryMapMock.containsKey("customerId")).thenReturn(false);
        inquiryRepository.postInquiry("customerId", inquiry);
        verify(inquiryMapMock).put(eq("customerId"), anyList());
    }
}
