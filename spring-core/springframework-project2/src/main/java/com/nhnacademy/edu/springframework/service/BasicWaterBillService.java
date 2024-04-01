package com.nhnacademy.edu.springframework.service;

import com.nhnacademy.edu.springframework.domain.WaterBill;
import com.nhnacademy.edu.springframework.repository.RateTableRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicWaterBillService implements WaterBillService {

    private RateTableRepository rateTableRepository;

    @Autowired
    public BasicWaterBillService(RateTableRepository rateTableRepository) {
        this.rateTableRepository = rateTableRepository;
    }

    @Override
    public List<WaterBill> fareCalculate(int usage) {
        List<WaterBill> waterBills = rateTableRepository.billCalculte(usage);
        for (WaterBill waterBill : waterBills) {
            waterBill.setBillTotal(waterBill.getUnitPrice() * usage);
        }
        return waterBills;
    }
}
