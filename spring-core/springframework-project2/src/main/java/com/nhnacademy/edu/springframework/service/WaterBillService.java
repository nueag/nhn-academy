package com.nhnacademy.edu.springframework.service;

import com.nhnacademy.edu.springframework.domain.WaterBill;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface WaterBillService {
    List<WaterBill> fareCalculate(int usage);
}
