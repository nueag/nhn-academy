package com.nhnacademy.nhnmart.domain;

import static com.nhnacademy.nhnmart.domain.Inquiry.formatter;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InquiryAnswer {
    private String content;
    private String answerDate;
    private String csAgent;

    public InquiryAnswer(String content, String csAgent) {
        this.content = content;
        this.answerDate = LocalDateTime.now().format(formatter);
        this.csAgent = csAgent;
    }

}
