package com.nhnacademy.assignment.service;

import com.nhnacademy.assignment.domain.dto.StudentResponse;

public interface StudentService {
    StudentResponse getStudent(Long studentId);

    StudentResponse registerStudent(StudentResponse studentResponse);

    StudentResponse updateStudent(Long studentId, StudentResponse studentResponse);
}
