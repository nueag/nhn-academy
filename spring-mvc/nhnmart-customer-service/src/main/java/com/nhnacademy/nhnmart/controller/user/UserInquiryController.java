package com.nhnacademy.nhnmart.controller.user;

import com.nhnacademy.nhnmart.domain.Category;
import com.nhnacademy.nhnmart.domain.Inquiry;
import com.nhnacademy.nhnmart.repository.InquiryRepository;
import com.nhnacademy.nhnmart.repository.UserRepository;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.tika.Tika;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user/inquiry")
public class UserInquiryController {

    private final UserRepository userRepository;
    private final InquiryRepository inquiryRepository;
    private static final String UPLOAD_DIR = "/Users/kaeun/Lesson/week2-spring-mvc/uploads/";

    public UserInquiryController(UserRepository userRepository, InquiryRepository inquiryRepository) {
        this.userRepository = userRepository;
        this.inquiryRepository = inquiryRepository;
    }

    @GetMapping
    public String getInquiry(HttpSession session, Model model) {
        String userId = getUserIdFromSession(session);
        model.addAttribute("inquiry", new Inquiry());
        model.addAttribute("userId", userId);
        return "thymeleaf/userInquiry";
    }

    @PostMapping
    public String postInquiry(HttpServletRequest request,
                              @RequestParam("title") String title,
                              @RequestParam("category") Category category,
                              @RequestParam("content") String content,
                              @RequestParam("file") List<MultipartFile> files) throws IOException {
        String userId = getUserIdFromSession(request.getSession());
        List<File> fileList = processFileUpload(files);
        Inquiry inquiry = new Inquiry(userId, title, category, content, fileList);
        inquiryRepository.postInquiry(userId, inquiry);
        return "redirect:/user/login/" + userId;
    }

    @GetMapping("/detail/{inquiryId}")
    public String getDetail(@PathVariable("inquiryId") long inquiryId,
                            HttpSession session,
                            Model model) {
        String userId = getUserIdFromSession(session);
        Inquiry inquiry = inquiryRepository.getInquiry(userId, inquiryId);
        model.addAttribute("inquiry", inquiry);
        model.addAttribute("user", userRepository.getUser(userId));
        return "thymeleaf/inquiryDetail";
    }

    @GetMapping("/category")
    public String getListByCategory(@RequestParam("category") String category,
                                    HttpSession session,
                                    Model model) {
        if (category.equals("전체")) {
            String userId = getUserIdFromSession(session);
            return "redirect:/user/login/" + userId;
        }
        List<Inquiry> inquiries = inquiryRepository.getInquiriesByCategory(category);
        model.addAttribute("inquiries", inquiries);
        model.addAttribute("categories", Category.getCategoryList());
        return "thymeleaf/customerService";
    }

    private String getUserIdFromSession(HttpSession session) {
        return (String) session.getAttribute("userId");
    }

    private List<File> processFileUpload(List<MultipartFile> files) throws IOException {
        List<File> fileList = new ArrayList<>();
        for (MultipartFile file : files) {
            System.out.println(file.getInputStream());
            if (!file.isEmpty() && checkImageMimeType(file.getInputStream())) {
                String filePath = Paths.get(UPLOAD_DIR, file.getOriginalFilename()).toString();
                file.transferTo(Paths.get(UPLOAD_DIR, file.getOriginalFilename()));
                fileList.add(new File(filePath));
            }
        }
        return fileList;
    }

    private boolean checkImageMimeType(InputStream file) throws IOException {
        Tika tika = new Tika();
        String mimeType = tika.detect(file);
        return mimeType.startsWith("image");
    }
}
