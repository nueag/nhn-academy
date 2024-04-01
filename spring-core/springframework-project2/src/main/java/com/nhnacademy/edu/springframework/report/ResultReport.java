package com.nhnacademy.edu.springframework.report;

import com.nhnacademy.edu.springframework.domain.WaterBill;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface ResultReport {
    void report(List<WaterBill> waterBills);
}
