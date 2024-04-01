package com.nhnacademy.exam.service.Impl;

import com.nhnacademy.exam.model.DepartmentEmployee;
import com.nhnacademy.exam.repository.DepartmentEmployeeRepository;
import com.nhnacademy.exam.repository.DepartmentRepository;
import com.nhnacademy.exam.repository.EmployeeRepository;
import com.nhnacademy.exam.responseDto.ResponseDepartmentMembers;
import com.nhnacademy.exam.service.DepartmentEmployeeService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultDepartmentEmployeeService implements DepartmentEmployeeService {
    private final DepartmentEmployeeRepository departmentEmployeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public DefaultDepartmentEmployeeService(DepartmentEmployeeRepository departmentEmployeeRepository,
                                            DepartmentRepository departmentRepository,
                                            EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.departmentEmployeeRepository = departmentEmployeeRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DepartmentEmployee registerInfo(DepartmentEmployee departmentEmployee) {
        if (!departmentRepository.existsById(departmentEmployee.getDepartment().getId())) {
            departmentRepository.saveAndFlush(departmentEmployee.getDepartment());
        }
        if (!employeeRepository.existsById(departmentEmployee.getEmployee().getEmployeeId())) {
            employeeRepository.saveAndFlush(departmentEmployee.getEmployee());
        }

        return departmentEmployeeRepository.saveAndFlush(departmentEmployee);
    }

    @Override
    public List<ResponseDepartmentMembers> getInfoByDepartmentId(String departmentId) {
        List<DepartmentEmployee> departmentInfos =
                departmentEmployeeRepository.findAllByDepartment_Id(departmentId);

        return departmentInfos.stream()
                .map(departmentEmployee -> modelMapper.map(departmentEmployee, ResponseDepartmentMembers.class))
                .collect(Collectors.toList());
    }
}
