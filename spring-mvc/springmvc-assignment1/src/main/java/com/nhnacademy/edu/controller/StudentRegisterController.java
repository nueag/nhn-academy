package com.nhnacademy.edu.controller;

import com.nhnacademy.edu.domain.Student;
import com.nhnacademy.edu.domain.StudentRegisterRequest;
import com.nhnacademy.edu.repository.StudentRepository;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student/register")
public class StudentRegisterController {
    private final StudentRepository studentRepository;

    public StudentRegisterController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String studentRegisterForm() {
        return "studentRegister";
    }

    @PostMapping
    public ModelAndView registerStudent(@Valid @ModelAttribute StudentRegisterRequest studentRequest) {
        studentRequest.isCommentValid();
        Student student = studentRepository.register(studentRequest.getName(), studentRequest.getEmail(),
                studentRequest.getScore(), studentRequest.getComment());
        ModelAndView mav = new ModelAndView("studentView");
        mav.addObject("student", student);
        return mav;
    }

}
