package com.nhnacademy.exam.parser.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.parser.DepartmentParser;
import com.nhnacademy.exam.responseDto.ParserResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class JsonDepartmentParser implements DepartmentParser {

    private final ObjectMapper objectMapper;
    private PathMatchingResourcePatternResolver patternResolver;

    @Override
    public String getFileType() {
        return "json";
    }

    @Override
    public List parsing(File file) throws IOException {
        String data = convertToString(file);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ParserResponse[] employee = objectMapper.readValue(data, ParserResponse[].class);
        return List.of(employee);
    }

    private String convertToString(File file) {
        String data = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            while ((data = br.readLine()) != null) {
                sb.append(data);
            }
            data = sb.toString();
        } catch (Exception e) {

        }
        return data;
    }
}
