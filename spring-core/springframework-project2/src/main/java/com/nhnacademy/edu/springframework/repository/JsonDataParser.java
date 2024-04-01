package com.nhnacademy.edu.springframework.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springframework.domain.WaterBill;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class JsonDataParser implements DataParser {
    @Override
    public List<WaterBill> parse(String filePath) {
        List<WaterBill> traiffList = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            traiffList = objectMapper.readValue(new File(filePath), new TypeReference<>() {
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return traiffList;
    }
}
