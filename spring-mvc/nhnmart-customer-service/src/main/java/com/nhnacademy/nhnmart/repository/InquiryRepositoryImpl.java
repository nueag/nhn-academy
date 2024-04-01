package com.nhnacademy.nhnmart.repository;

import com.nhnacademy.nhnmart.domain.Inquiry;
import com.nhnacademy.nhnmart.domain.InquiryAnswer;
import com.nhnacademy.nhnmart.exception.InquiryNotFoundException;
import com.nhnacademy.nhnmart.exception.UserNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InquiryRepositoryImpl implements InquiryRepository {
    private Map<String, List<Inquiry>> inquiryMap = new HashMap<>();

    private boolean isExistUser(String userId) {
        return inquiryMap.containsKey(userId);
    }

    public List<Inquiry> getAllInquiries() {
        List<Inquiry> allInquiries = new ArrayList<>();
        for (List<Inquiry> inquiryList : inquiryMap.values()) {
            for (Inquiry inquiry : inquiryList) {
                allInquiries.add(inquiry);
            }
        }
        return allInquiries;
    }

    @Override
    public List<Inquiry> getInquiriesByUserId(String userId) {
        if (!isExistUser(userId)) {
            throw new UserNotFoundException();
        }
        return inquiryMap.get(userId);
    }

    @Override
    public List<Inquiry> getInquiriesByCategory(String category) {
        return getAllInquiries().stream()
                .filter(inquiry -> inquiry.getCategory().getDisplayName().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Inquiry> getInquiriesByUnAnswered() {
        List<Inquiry> inquiries = getAllInquiries();
        return inquiries.stream()
                .filter(inquiry -> !inquiry.isAnswerCompleted())
                .collect(Collectors.toList());
    }

    @Override
    public Inquiry getInquiry(String userId, long inquiryId) {
        if (!isExistUser(userId)) {
            throw new UserNotFoundException();
        }
        List<Inquiry> inquiries = inquiryMap.get(userId).stream()
                .filter(inquiry -> inquiry.getInquiryId() == inquiryId)
                .collect(Collectors.toList());
        if (inquiries.size() == 0) {
            throw new InquiryNotFoundException();
        }
        return inquiries.get(0);
    }

    @Override
    public void postInquiry(String userId, Inquiry inquiry) {
        List<Inquiry> inquiries = new ArrayList<>();
        if (!isExistUser(userId)) {
            inquiries.add(inquiry);
            inquiryMap.put(userId, inquiries);
        } else {
            inquiries.add(inquiry);
            inquiryMap.get(userId).addAll(0, inquiries);
        }
    }


    @Override
    public void setAnswer(String userId, long inquiryId, InquiryAnswer answer) {
        getInquiry(userId, inquiryId).setAnswer(answer);
    }
}
