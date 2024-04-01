package com.nhnacademy.nhnmart.config;


import com.nhnacademy.nhnmart.Base;
import com.nhnacademy.nhnmart.domain.Category;
import com.nhnacademy.nhnmart.domain.Inquiry;
import com.nhnacademy.nhnmart.domain.InquiryAnswer;
import com.nhnacademy.nhnmart.repository.InquiryRepository;
import com.nhnacademy.nhnmart.repository.InquiryRepositoryImpl;
import com.nhnacademy.nhnmart.repository.UserRepository;
import com.nhnacademy.nhnmart.repository.UserRepositoryImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
        excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {
    @Bean
    public UserRepository userRepository() {
        UserRepository userRepository = new UserRepositoryImpl();
        userRepository.addCustomer("customer", "12345", "gaeun");
        userRepository.addAdmin("admin", "12345", "gaeun");

        return userRepository;
    }

    @Bean
    public InquiryRepository inquiryRepository() {
        InquiryRepository inquiryRepository = new InquiryRepositoryImpl();
        String filePath = "/Users/kaeun/Lesson/week2-spring-mvc/uploads/seifjdk.jpeg";
        List<File> files = new ArrayList<>();
        files.add(new File(filePath));
        Inquiry inquiry =
                new Inquiry("customer", "반품하고 싶어요ㅠ", Category.REFUND_EXCHANGE, "인형 솜이 보시면 다 찢어져 있어요ㅠㅠ 교환할 수 있을까요?",
                        files);
        inquiryRepository.postInquiry("customer", inquiry);
        InquiryAnswer answer = new InquiryAnswer("답변드립니다", "간");
        inquiryRepository.setAnswer(inquiry.getCustomerId(), inquiry.getInquiryId(), answer);
        return inquiryRepository;
    }

}
