package com.nhnacademy.edu.controller;

import com.nhnacademy.edu.domain.Student;
import com.nhnacademy.edu.domain.StudentModifyRequest;
import com.nhnacademy.edu.domain.StudentRegisterRequest;
import com.nhnacademy.edu.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StudentsController {
    private StudentRepository studentRepository;

    @Autowired
    public StudentsController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping(value = "/students/{studentId}", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Student> getStudentJson(@PathVariable Long studentId) {
        Student student = studentRepository.getStudent(studentId);
        if (student != null) {
            String etag = "1234";

            return ResponseEntity.ok()
                    .eTag(etag)
                    .body(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/students", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Student> registerJsonStudent(@RequestBody StudentRegisterRequest studentRegisterRequest) {

        Student student =
                studentRepository.register(studentRegisterRequest.getName(), studentRegisterRequest.getEmail(),
                        studentRegisterRequest.getScore(), studentRegisterRequest.getComment());
        return ResponseEntity.ok()
                .body(student);
    }


    @PutMapping(value = "/students/{studentId}", produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Student> updateStudent(@PathVariable("studentId") Long studentId,
                                                 @RequestBody StudentModifyRequest updatedStudent) {
        Student existingStudent = studentRepository.getStudent(studentId);
        Student student =
                new Student(studentId, updatedStudent.getName(), updatedStudent.getEmail(), updatedStudent.getScore(),
                        updatedStudent.getComment());

        if (existingStudent != null) {
            studentRepository.modify(student);
            return ResponseEntity.ok()
                    .body(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
