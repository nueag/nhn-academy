package com.nhnacademy.certification.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class ResidentCertificateInfo {
    String name = "";
    String houseMovementAddress ="";
    String houseMovementReportDate;


    public ResidentCertificateInfo(String name,
                                   String houseMovementAddress, String houseMovementReportDate) {
        this.name = name;
        this.houseMovementAddress = houseMovementAddress;
        this.houseMovementReportDate = houseMovementReportDate;
    }
}
