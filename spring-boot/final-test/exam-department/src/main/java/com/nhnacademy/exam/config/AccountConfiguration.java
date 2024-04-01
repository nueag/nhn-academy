package com.nhnacademy.exam.config;

import com.nhnacademy.exam.initialSetting;
import com.nhnacademy.exam.service.Impl.DefaultDepartmentEmployeeService;
import com.nhnacademy.exam.service.Impl.DefaultDepartmentService;
import com.nhnacademy.exam.service.Impl.DefaultEmployeeService;
import java.io.IOException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }





}
