package com.nhnacademy.edu.springframework.repository;

import com.nhnacademy.edu.springframework.domain.WaterBill;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CsvDataParser implements DataParser {

    @Override
    public List<WaterBill> parse(String filePath) {
        List<WaterBill> traiffList = new ArrayList<>();
        File csv = new File(filePath);
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(csv));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] oneLine = line.split(",");
                WaterBill waterBIll = new WaterBill(Integer.parseInt(oneLine[0]), oneLine[1], oneLine[2],
                        Integer.parseInt(oneLine[3]),
                        Integer.parseInt(oneLine[4]), Integer.parseInt(oneLine[5]), Integer.parseInt(oneLine[6]), 0);
                traiffList.add(waterBIll);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return traiffList;
    }
}
