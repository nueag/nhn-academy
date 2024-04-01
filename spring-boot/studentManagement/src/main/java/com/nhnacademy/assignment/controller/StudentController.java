package com.nhnacademy.assignment.controller;

import com.nhnacademy.assignment.controller.request.StudentRegisterRequest;
import com.nhnacademy.assignment.domain.dto.StudentResponse;
import com.nhnacademy.assignment.exception.StudentNotFoundException;
import com.nhnacademy.assignment.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @ModelAttribute("student")
    public StudentResponse addStudentToModel(@PathVariable("studentId") long studentId,
                                             Model model) {
        StudentResponse student = studentService.getStudent(studentId);
        model.addAttribute("student", student);
        return student;
    }

    @GetMapping("/{studentId}")
    public String viewStudent(Model model) {
        model.addAttribute("hideScore", true);
        return "studentView";
    }

    @GetMapping(path = "/{studentId}", params = "hideScore")
    public String hideScoreViewStudent(@RequestParam("hideScore") String hideScore,
                                       Model model) {
        if (hideScore.equals("yes")) {
            model.addAttribute("hideScore", false);
        }
        return "studentView";
    }

    @GetMapping("/{studentId}/modify")
    public String studentModifyForm(Model model) {
        model.addAttribute("hideScore", true);
        return "studentModify";
    }

    @PostMapping("/{studentId}/modify")
    public String modifyUser(@PathVariable("studentId") long studentId,
                             @ModelAttribute StudentRegisterRequest request,     // 수정 요청 객체
                             ModelMap modelmap) {

        StudentResponse student =
                new StudentResponse(studentId, request.getName(), request.getEmail(), request.getScore(),
                        request.getComment());

        StudentResponse update = studentService.updateStudent(studentId, student);
        modelmap.addAttribute("student", update);
        modelmap.addAttribute("hideScore", true);
        return "studentView";
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundException(Exception ex, Model model) {
        log.error("", ex);

        model.addAttribute("exception", "404 exception");
        return "error";
    }

}
