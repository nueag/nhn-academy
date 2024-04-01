package com.nhnacademy.nhnmart.repository;

import com.nhnacademy.nhnmart.domain.Inquiry;
import com.nhnacademy.nhnmart.domain.InquiryAnswer;
import java.util.List;

public interface InquiryRepository {

    List<Inquiry> getInquiriesByUserId(String userId);

    List<Inquiry> getInquiriesByCategory(String category);

    List<Inquiry> getInquiriesByUnAnswered();

    Inquiry getInquiry(String userId, long inquiryId);

    void postInquiry(String userId, Inquiry inquiry);

    void setAnswer(String userId, long inquiryId, InquiryAnswer answer);
}
