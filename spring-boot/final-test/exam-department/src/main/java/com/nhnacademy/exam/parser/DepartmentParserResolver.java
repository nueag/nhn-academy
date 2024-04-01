package com.nhnacademy.exam.parser;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentParserResolver {
    private final List<DepartmentParser> departmentParserList;

    public DepartmentParser getDepartmentParser(String fileName) {
        for (DepartmentParser departmentParser : departmentParserList) {
            if (departmentParser.matchFileType(fileName)) {
                return departmentParser;
            }
        }
        return null;
    }


}
