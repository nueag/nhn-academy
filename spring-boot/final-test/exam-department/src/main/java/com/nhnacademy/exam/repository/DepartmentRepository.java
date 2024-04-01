package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {

}
