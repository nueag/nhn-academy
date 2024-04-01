package com.nhnacademy.exam.responseDto;

import com.nhnacademy.exam.model.Department;
import com.nhnacademy.exam.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDepartmentMembers {
    private Department department;
    private Employee employee;
}
