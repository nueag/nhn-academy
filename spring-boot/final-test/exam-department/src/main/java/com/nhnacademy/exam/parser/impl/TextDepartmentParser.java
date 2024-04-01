package com.nhnacademy.exam.parser.impl;

import com.nhnacademy.exam.parser.DepartmentParser;
import com.nhnacademy.exam.responseDto.ParserResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TextDepartmentParser implements DepartmentParser {

    @Override
    public String getFileType() {
        return "txt";
    }

    @Override
    public List parsing(File file) throws IOException {
        String pattern = "|{0}|{1}|{2}|{3}|";
        MessageFormat mf = new MessageFormat(pattern);
        List<ParserResponse> parserResponses = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String data;
            while ((data = br.readLine()) != null) {
                if (data.contains("-") || data.contains("사번")) {
                    continue;
                }
                Object[] objects = mf.parse(data.replace(" ", ""));
                parserResponses.add(new ParserResponse((String) objects[0], (String) objects[1], (String) objects[2],
                        (String) objects[3]));
            }
        } catch (Exception e) {

        }
        return parserResponses;
    }
}
