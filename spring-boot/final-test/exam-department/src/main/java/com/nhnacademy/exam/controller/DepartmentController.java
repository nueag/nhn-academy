package com.nhnacademy.exam.controller;

import com.nhnacademy.exam.requestDto.DepartmentRequest;
import com.nhnacademy.exam.requestDto.RequestName;
import com.nhnacademy.exam.responseDto.ResponseId;
import com.nhnacademy.exam.responseDto.ResponseIdAndName;
import com.nhnacademy.exam.service.Impl.DefaultDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {
    private final DefaultDepartmentService departmentService;

    @PostMapping
    public ResponseId registerDepartment(@RequestBody DepartmentRequest departmentRequest) {
        return departmentService.registerDepartment(departmentRequest);
    }

    @GetMapping("/{id}")
    public ResponseIdAndName getDepartmentById(@PathVariable("id") String id) {
        return departmentService.getDepartment(id);
    }

    @PutMapping("/{id}")
    public void updateDepartment(@PathVariable("id") String id,
                                 @RequestBody RequestName requestName) {
        departmentService.updateDepartment(new DepartmentRequest(id, requestName.getName()));
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable("id") String id) {
        departmentService.deleteDepartment(id);
    }


}
