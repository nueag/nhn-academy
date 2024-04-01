package com.nhnacademy.edu.controller;

import com.nhnacademy.edu.repository.StudentRepository;
import com.nhnacademy.edu.repository.UserRepository;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login(@CookieValue(value = "COOKIE", required = false) String session,
                        Model model) {
        if (StringUtils.hasText(session)) {
            model.addAttribute("id", session);
            return "thymeleaf/loginSuccess";
        } else {
            return "thymeleaf/loginForm";
        }
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("pwd") String pwd,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          ModelMap modelMap) {
        if (userRepository.matches(id, pwd)) {
            HttpSession session = request.getSession(true);

            Cookie cookie = new Cookie("COOKIE", session.getId());
            response.addCookie(cookie);

            modelMap.put("id", session.getId());
            return "thymeleaf/loginSuccess";
        } else {
            return "redirect:/login";
        }
    }

}
