package com.nhnacademy.nhnmart.controller.user;

import com.nhnacademy.nhnmart.domain.Category;
import com.nhnacademy.nhnmart.domain.User;
import com.nhnacademy.nhnmart.exception.UserNotFoundException;
import com.nhnacademy.nhnmart.repository.InquiryRepository;
import com.nhnacademy.nhnmart.repository.UserRepository;
import java.util.Objects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final InquiryRepository inquiryRepository;

    public UserController(UserRepository userRepository, InquiryRepository inquiryRepository) {
        this.userRepository = userRepository;
        this.inquiryRepository = inquiryRepository;
    }

    @ModelAttribute("user")
    public User getUser(@PathVariable("userId") String userId) {
        User user = userRepository.getUser(userId);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException();
        }

        return user;
    }

    @GetMapping("/login/{userId}")
    public String loginUser(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("inquiries", inquiryRepository.getInquiriesByUserId(userId));
        model.addAttribute("categories", Category.getCategoryList());
        model.addAttribute("userId", userId);
        return "thymeleaf/customerService";
    }
}
