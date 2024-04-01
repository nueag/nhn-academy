package com.nhnacademy.assignment.controller;

import com.nhnacademy.assignment.controller.request.StudentRegisterRequest;
import com.nhnacademy.assignment.domain.dto.StudentResponse;
import com.nhnacademy.assignment.service.StudentService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student/register")
@RequiredArgsConstructor
public class StudentRegisterController {
    private final StudentService studentService;

    @GetMapping
    public String studentRegisterForm() {
        return "studentRegister";
    }

    @PostMapping
    public ModelAndView registerStudent(@Valid @ModelAttribute StudentRegisterRequest studentRequest) {
        StudentResponse student = studentService.registerStudent(
                new StudentResponse(studentRequest.getName(), studentRequest.getEmail(),
                        studentRequest.getScore(), studentRequest.getComment()));

        ModelAndView mav = new ModelAndView("studentView");
        mav.addObject("student", student);
        mav.addObject("hideScore", true);
        return mav;
    }

}
