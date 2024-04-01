package com.nhnacademy.exam.service;

import com.nhnacademy.exam.requestDto.DepartmentRequest;
import com.nhnacademy.exam.responseDto.ResponseId;
import com.nhnacademy.exam.responseDto.ResponseIdAndName;

public interface DepartmentService {
    ResponseId registerDepartment(DepartmentRequest departmentRequest);

    ResponseIdAndName getDepartment(String id);

    void updateDepartment(DepartmentRequest departmentRequest);

    void deleteDepartment(String id);
}
