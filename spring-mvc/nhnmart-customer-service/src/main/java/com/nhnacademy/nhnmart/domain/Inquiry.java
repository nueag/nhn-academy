package com.nhnacademy.nhnmart.domain;

import com.nhnacademy.nhnmart.exception.AnswerAlreadyExistException;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Inquiry {
    private String customerId;
    private long inquiryId;
    @Setter
    private InquiryAnswer answer;
    private String title;
    private Category category;
    private String content;
    private String postDate;
    private List<File> files;
    @Setter
    private boolean isAnswerCompleted;

    private static long INQUIRY_ID_COUNT = 1;
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Inquiry(String customerId, String title, Category category, String content, List<File> files) {
        this.customerId = customerId;
        this.inquiryId = getAutoIncrementId();
        this.answer = null;
        this.title = title;
        this.category = category;
        this.content = content;
        this.postDate = LocalDateTime.now().format(formatter);
        this.files = files;
        this.isAnswerCompleted = false;
    }

    private long getAutoIncrementId() {
        return INQUIRY_ID_COUNT++;
    }

    public void setAnswer(InquiryAnswer answer) {
        if (isAnswerCompleted) {
            throw new AnswerAlreadyExistException();
        }
        isAnswerCompleted(true);
        this.answer = answer;
    }

    private void isAnswerCompleted(boolean isCompleted) {
        this.isAnswerCompleted = isCompleted;
    }
}
