package com.nhnacademy.assignment.repository;

import com.nhnacademy.assignment.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
