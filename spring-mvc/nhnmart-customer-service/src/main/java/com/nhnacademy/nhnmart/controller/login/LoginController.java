package com.nhnacademy.nhnmart.controller.login;

import com.nhnacademy.nhnmart.domain.User;
import com.nhnacademy.nhnmart.repository.UserRepository;
import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "thymeleaf/loginForm";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("pwd") String pwd,
                          HttpServletRequest request,
                          HttpServletResponse response) throws FailedLoginException {
        if (userRepository.matches(id, pwd)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userId", id);
            Cookie cookie = new Cookie("COOKIE", session.getId());
            cookie.setMaxAge(300);
            response.addCookie(cookie);

            User user = userRepository.getUser(id);
            if (user.isAdmin()) {
                return "redirect:/admin/login/" + id;
            }
            return "redirect:/user/login/" + id;
        } else {
            throw new FailedLoginException();
        }
    }

    @GetMapping("/login/{userId}")
    public String doPage(@PathVariable("userId") String userId) {
        User user = userRepository.getUser(userId);
        if (user.isAdmin()) {
            return "redirect:/admin/login/" + userId;
        } else {
            return "redirect:/user/login/" + userId;
        }
    }

}
