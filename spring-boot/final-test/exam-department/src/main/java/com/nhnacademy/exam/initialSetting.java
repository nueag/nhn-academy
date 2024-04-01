package com.nhnacademy.exam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.model.Department;
import com.nhnacademy.exam.model.DepartmentEmployee;
import com.nhnacademy.exam.model.Employee;
import com.nhnacademy.exam.parser.DepartmentParserResolver;
import com.nhnacademy.exam.parser.impl.CsvDepartmentParser;
import com.nhnacademy.exam.parser.impl.JsonDepartmentParser;
import com.nhnacademy.exam.parser.impl.TextDepartmentParser;
import com.nhnacademy.exam.requestDto.DepartmentRequest;
import com.nhnacademy.exam.responseDto.ParserResponse;
import com.nhnacademy.exam.service.Impl.DefaultDepartmentEmployeeService;
import com.nhnacademy.exam.service.Impl.DefaultDepartmentService;
import com.nhnacademy.exam.service.Impl.DefaultEmployeeService;
import java.io.IOException;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

@Component
public class initialSetting {
    private final DefaultDepartmentEmployeeService defaultDepartmentEmployeeService;
    private final DepartmentParserResolver departmentParserResolver;
    TextDepartmentParser textDepartmentParser = new TextDepartmentParser();
    CsvDepartmentParser csvDepartmentParser = new CsvDepartmentParser();
    JsonDepartmentParser jsonDepartmentParser = new JsonDepartmentParser(new ObjectMapper());

    public initialSetting(DefaultDepartmentEmployeeService defaultDepartmentEmployeeService) throws IOException {
        this.defaultDepartmentEmployeeService = defaultDepartmentEmployeeService;
        departmentParserResolver = new DepartmentParserResolver(
                List.of(textDepartmentParser, jsonDepartmentParser, csvDepartmentParser));
        setting();
    }

    public void setting() throws IOException {
        List<String> files = List.of("data/department-1.txt", "data/department-2.txt");
        for(String filePath : files){
            Resource resource = new PathMatchingResourcePatternResolver().getResource("classpath:" + filePath);
            List<ParserResponse> responses = departmentParserResolver
                    .getDepartmentParser(filePath)
                    .parsing(resource.getFile());

            insertValues(responses);
        }
    }

    private void insertValues(List<ParserResponse> responses) {
        for(ParserResponse object : responses) {
            Department department = new Department(object.getCode(), object.getDepartment());
            Employee employee = new Employee(object.getEmployeeId(), object.getName());
            defaultDepartmentEmployeeService.registerInfo(new DepartmentEmployee(department,employee));
        }
    }

}
