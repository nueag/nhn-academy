package com.nhnacademy.exam.service.Impl;

import com.nhnacademy.exam.model.Employee;
import com.nhnacademy.exam.repository.EmployeeRepository;
import com.nhnacademy.exam.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultEmployeeService implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public DefaultEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

}
