package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.model.DepartmentEmployee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentEmployeeRepository extends JpaRepository<DepartmentEmployee, Long> {

    List<DepartmentEmployee> findAllByDepartment_Id(String departmentId);
}
