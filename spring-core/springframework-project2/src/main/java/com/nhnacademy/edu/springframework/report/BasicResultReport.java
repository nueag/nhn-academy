package com.nhnacademy.edu.springframework.report;

import com.nhnacademy.edu.springframework.domain.WaterBill;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BasicResultReport implements ResultReport {

    @Override
    public void report(List<WaterBill> waterBills) {
        waterBills.stream()
                .limit(5)
                .forEach(waterBill -> System.out.println(waterBill.toString()));
    }
}
