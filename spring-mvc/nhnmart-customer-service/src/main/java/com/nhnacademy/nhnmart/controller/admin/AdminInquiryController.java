package com.nhnacademy.nhnmart.controller.admin;

import com.nhnacademy.nhnmart.domain.Inquiry;
import com.nhnacademy.nhnmart.domain.InquiryAnswer;
import com.nhnacademy.nhnmart.domain.User;
import com.nhnacademy.nhnmart.repository.InquiryRepository;
import com.nhnacademy.nhnmart.repository.UserRepository;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/inquiry")
public class AdminInquiryController {
    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;


    public AdminInquiryController(InquiryRepository inquiryRepository, UserRepository userRepository) {
        this.inquiryRepository = inquiryRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/detail/{customerId}/{inquiryId}")
    public String getDetail(@PathVariable("inquiryId") long inquiryId,
                            @PathVariable("customerId") String customerId,
                            HttpSession session,
                            Model model) {
        User user = userRepository.getUser((String) session.getAttribute("userId"));
        Inquiry inquiry = inquiryRepository.getInquiry(customerId, inquiryId);
        model.addAttribute("inquiry", inquiry);
        model.addAttribute("user", user);
        return "thymeleaf/inquiryDetail";
    }

    @GetMapping("/answer/{customerId}/{inquiryId}")
    public String answerPage(@PathVariable("customerId") String customerId,
                             @PathVariable("inquiryId") long inquiryId,
                             Model model) {
        Inquiry inquiry = inquiryRepository.getInquiry(customerId, inquiryId);
        model.addAttribute("inquiry", inquiry);
        return "thymeleaf/answerPage";
    }

    @PostMapping("/answer/{customerId}/{inquiryId}")
    public String answerCompleted(@PathVariable("customerId") String customerId,
                                  @PathVariable("inquiryId") long inquiryId,
                                  @RequestParam("answerText") String answerText,
                                  HttpSession session) {
        String adminId = (String) session.getAttribute("userId");
        inquiryRepository.setAnswer(customerId, inquiryId, new InquiryAnswer(answerText, adminId));
        return "redirect:/admin/login/" + adminId;
    }


}