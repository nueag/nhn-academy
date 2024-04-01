package com.nhnacademy.assignment.controller;

import com.nhnacademy.assignment.domain.dto.StudentResponse;
import com.nhnacademy.assignment.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StudentsController {
    private final StudentService studentService;

    @GetMapping("/students/{studentId}")
    public StudentResponse getStudentJson(@PathVariable Long studentId) {
        return studentService.getStudent(studentId);
    }

    @PostMapping("/students")
    public StudentResponse registerJsonStudent(@RequestBody StudentResponse studentRegisterRequest) {
        return studentService.registerStudent(studentRegisterRequest);
    }


    @PutMapping("/students/{studentId}")
    public StudentResponse updateStudent(@PathVariable("studentId") Long studentId,
                                         @RequestBody StudentResponse updatedStudent) {
        return studentService.updateStudent(studentId, updatedStudent);
    }

}
