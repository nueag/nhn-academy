package com.nhnacademy.nhnmart.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Category {
    COMPLAINT("불만 접수"),
    SUGGESTION("제안"),
    REFUND_EXCHANGE("환불/교환"),
    COMPLIMENT("칭찬해요"),
    OTHER_INQUIRY("기타 문의");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static List<String> getCategoryList() {
        return Arrays.stream(values())
                .map(Category::getDisplayName)
                .collect(Collectors.toList());
    }
}

