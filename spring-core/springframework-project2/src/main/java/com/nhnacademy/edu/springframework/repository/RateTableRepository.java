package com.nhnacademy.edu.springframework.repository;

import com.nhnacademy.edu.springframework.domain.WaterBill;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface RateTableRepository {
    void parse(String filePath);

    List<WaterBill> billCalculte(int usage);
}
