package com.nhnacademy.edu.springframework;

import com.nhnacademy.edu.springframework.report.ResultReport;
import com.nhnacademy.edu.springframework.service.DataLoadService;
import com.nhnacademy.edu.springframework.service.WaterBillService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BootStrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework");
        DataLoadService dataLoadService = context.getBean("basicDataLoadService", DataLoadService.class);
        dataLoadService.load("trariff.csv");

        WaterBillService waterBillService = context.getBean("basicWaterBillService", WaterBillService.class);

        ResultReport resultReport = context.getBean("basicResultReport", ResultReport.class);
        resultReport.report(waterBillService.fareCalculate(1000));
    }
}
