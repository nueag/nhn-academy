//package com.nhnacademy.exam.service.Impl;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.BDDMockito.given;
//
//import com.nhnacademy.exam.model.Department;
//import com.nhnacademy.exam.repository.DepartmentRepository;
//import com.nhnacademy.exam.requestDto.DepartmentRequest;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ActiveProfiles;
//
//@SpringBootTest
//@ActiveProfiles("test")
//class DefaultDepartmentServiceTest {
//
//    @Autowired
//    DefaultDepartmentService defaultDepartmentService;
//
//    @MockBean
//    DepartmentRepository departmentRepository;
//
//    @MockBean
//    ModelMapper modelMapper;
//
//    @Test
//    @DisplayName("부서 등록 테스트")
//    void testRegisterDepartment() {
//        Department department = new Department("nhn1", "nhn-department");
//
//        given(departmentRepository.existsById(anyString())).willReturn(false);
//        given(departmentRepository.save(any(Department.class))).willReturn(department);
//
//        Assertions.assertThat(defaultDepartmentService.registerDepartment(new DepartmentRequest(department.getId(),department.getName())).getId()).isEqualTo(department.getId());
//    }
//
//    @Test
//    @DisplayName("부서 등록 테스트 : 실패")
//    void testRegisterDepartmentX() {
//        Department department = new Department("nhn1", "nhn-department");
//
//        given(departmentRepository.existsById(anyString())).willReturn(true);
//        given(departmentRepository.save(any(Department.class))).willReturn(department);
//
//        Assertions.assertThat(defaultDepartmentService.registerDepartment(new DepartmentRequest(department.getId(),department.getName()))).isEqualTo(department);
//    }
//}