package com.nhnacademy.assignment.service;

import com.nhnacademy.assignment.domain.Student;
import com.nhnacademy.assignment.domain.dto.StudentResponse;
import com.nhnacademy.assignment.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultStudentService implements StudentService {
    private final StudentRepository studentRepository;

    public DefaultStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponse getStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);

        if (student != null) {
            return new StudentResponse(student.getId(), student.getName(), student.getEmail(), student.getScore(),
                    student.getComment());
        } else {
            throw new RuntimeException("student not found!");
        }
    }

    @Override
    public StudentResponse registerStudent(StudentResponse request) {
        System.out.println(request.toString());
        Student student = new Student(request.getName(), request.getEmail(), request.getScore(),
                request.getComment());
        Student register = studentRepository.save(student);
        request.setId(register.getId());
        return request;
    }

    @Override
    public StudentResponse updateStudent(Long studentId, StudentResponse request) {
        if (!studentRepository.existsById(studentId)) {
            throw new RuntimeException("student not found");
        }
        Student update = new Student(studentId, request.getName(), request.getEmail(), request.getScore(),
                request.getComment());
        update = studentRepository.save(update);
        return new StudentResponse(update.getId(), update.getName(), update.getEmail(), update.getScore(),
                update.getComment());
    }
}
