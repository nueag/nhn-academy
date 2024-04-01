package com.nhnacademy.edu.controller;

import com.nhnacademy.edu.domain.Student;
import com.nhnacademy.edu.exception.StudentNotFoundException;
import com.nhnacademy.edu.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StudentsController {
    private final StudentRepository studentRepository;

    public StudentsController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students/{studentId}")
    @ResponseBody
    public ResponseEntity<Student> getStudentJson(@PathVariable Long studentId) {
        Student student = studentRepository.getStudent(studentId);
        if (student != null) {
            String etag = "1234";

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .eTag(etag)
                    .body(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/students")
    public ResponseEntity<Student> registerJsonStudent(@RequestBody Student student) {
        studentRepository.register(student.getName(), student.getEmail(), student.getScore(), student.getComment());
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(student);
    }


    @PutMapping("/students/{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable("studentId") Long studentId,
                                                @RequestBody Student updatedStudent) {
        Student existingStudent = studentRepository.getStudent(studentId);

        if (existingStudent != null) {
            studentRepository.modify(updatedStudent);
            return new ResponseEntity<>("Student updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundException(Exception ex, Model model) {
        log.error("", ex);

        model.addAttribute("exception", "404 exception");
        return "thymeleaf/error";
    }

}
