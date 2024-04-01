package com.nhnacademy.edu.springframework.repository;

import com.nhnacademy.edu.springframework.domain.WaterBill;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface DataParser {
    List<WaterBill> parse(String filePath);
}
