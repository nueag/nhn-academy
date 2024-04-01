package com.nhnacademy.edu.repository;

import com.nhnacademy.edu.domain.Student;

public interface StudentRepository {
    boolean exists(long id);

    Student register(String name, String email, int score, String comment);

    Student getStudent(long id);

    void modify(Student student);
}
