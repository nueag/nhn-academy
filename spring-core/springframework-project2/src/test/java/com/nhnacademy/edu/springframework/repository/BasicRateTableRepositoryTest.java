package com.nhnacademy.edu.springframework.repository;

import com.nhnacademy.edu.springframework.config.JavaConfig;
import com.nhnacademy.edu.springframework.domain.WaterBill;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(JavaConfig.class)
class BasicRateTableRepositoryTest {
    @Autowired
    private RateTableRepository basicRateTableRepository;

    @Test
    @DisplayName("csv 파싱 테스트")
    void csvParseTest() {
        basicRateTableRepository.parse("trariff.csv");
        List<WaterBill> waterBills = ((BasicRateTableRepository) basicRateTableRepository).getWaterBills();
        Assertions.assertEquals(waterBills.size(), 15);
        Assertions.assertEquals(waterBills.get(0).getUnitPrice(), 40);
    }

}