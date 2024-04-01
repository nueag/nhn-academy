package com.nhnacademy.exam.controller;

import com.nhnacademy.exam.exception.ParameterNotFoundException;
import com.nhnacademy.exam.responseDto.ResponseDepartmentMembers;
import com.nhnacademy.exam.service.DepartmentEmployeeService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/department-members")
public class DepartmentMemberController {
    private final DepartmentEmployeeService departmentEmployeeService;

    @GetMapping
    public List<ResponseDepartmentMembers> getMembersByDepartmentId(
            @RequestParam(value = "departmentIds", required = false) String departmentId) {
        if (("").equals(departmentId) || Objects.isNull(departmentId)) {
            throw new ParameterNotFoundException(
                    "Required request parameter 'departmentIds' for method parameter type String is not present");
        }
        return departmentEmployeeService.getInfoByDepartmentId(departmentId);
    }

}