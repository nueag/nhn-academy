package com.nhnacademy.exam.service.Impl;

import com.nhnacademy.exam.exception.DepartmentIdAlreadyExist;
import com.nhnacademy.exam.exception.DepartmentNotExistException;
import com.nhnacademy.exam.model.Department;
import com.nhnacademy.exam.repository.DepartmentRepository;
import com.nhnacademy.exam.requestDto.DepartmentRequest;
import com.nhnacademy.exam.responseDto.ResponseId;
import com.nhnacademy.exam.responseDto.ResponseIdAndName;
import com.nhnacademy.exam.service.DepartmentService;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultDepartmentService implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DefaultDepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ResponseId registerDepartment(DepartmentRequest departmentRequest) {
        if (departmentRepository.existsById(departmentRequest.getId())) {
            throw new DepartmentIdAlreadyExist("부서 아이디 중복 : " + departmentRequest.getId());
        }
        Department department =
                departmentRepository.save(new Department(departmentRequest.getId(), departmentRequest.getName()));
        return modelMapper.map(department, ResponseId.class);
    }

    @Override
    public ResponseIdAndName getDepartment(String id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isEmpty()) {
            throw new DepartmentNotExistException(id);
        }
        return modelMapper.map(departmentOptional.get(), ResponseIdAndName.class);
    }

    @Override
    public void updateDepartment(DepartmentRequest departmentRequest) {
        if (!departmentRepository.existsById(departmentRequest.getId())) {
            throw new DepartmentNotExistException(departmentRequest.getId());
        }
        departmentRepository.save(new Department(departmentRequest.getId(), departmentRequest.getName()));
    }

    @Override
    public void deleteDepartment(String id) {
        departmentRepository.deleteById(id);
    }
}
