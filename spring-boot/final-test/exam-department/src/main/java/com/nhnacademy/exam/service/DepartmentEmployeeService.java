package com.nhnacademy.exam.service;

import com.nhnacademy.exam.model.DepartmentEmployee;
import com.nhnacademy.exam.responseDto.ResponseDepartmentMembers;
import java.util.List;

public interface DepartmentEmployeeService {
    DepartmentEmployee registerInfo(DepartmentEmployee departmentEmployee);
    List<ResponseDepartmentMembers> getInfoByDepartmentId(String departmentId);
}
