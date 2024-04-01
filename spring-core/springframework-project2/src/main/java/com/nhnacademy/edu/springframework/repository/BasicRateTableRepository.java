package com.nhnacademy.edu.springframework.repository;

import com.nhnacademy.edu.springframework.domain.WaterBill;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Getter
@Component
public class BasicRateTableRepository implements RateTableRepository {
    private DataParser jsonDataParser;
    private DataParser csvDataParser;
    private List<WaterBill> waterBills;

    @Autowired
    public BasicRateTableRepository(@Qualifier("jsonDataParser") DataParser jsonDataParser,
                                    @Qualifier("csvDataParser") DataParser csvDataParser) {
        this.jsonDataParser = jsonDataParser;
        this.csvDataParser = csvDataParser;
    }

    @Override
    public void parse(String filePath) {
        this.waterBills = new ArrayList<>();
        String fileExtension = filePath.substring(filePath.indexOf(".") + 1);
        if ("json".equalsIgnoreCase(fileExtension)) {
            this.waterBills = jsonDataParser.parse(filePath);
        } else if ("csv".equalsIgnoreCase(fileExtension)) {
            this.waterBills = csvDataParser.parse(filePath);
        } else {
            throw new IllegalArgumentException("Unsupported file extension: " + fileExtension);
        }
    }

    @Override
    public List<WaterBill> billCalculte(int usage) {
        return waterBills.stream()
                .filter(waterBill -> waterBill.getStartSection() <= usage && waterBill.getEndSection() >= usage)
                .sorted(Comparator.comparing(WaterBill::getUnitPrice))
                .collect(Collectors.toList());
    }
}
