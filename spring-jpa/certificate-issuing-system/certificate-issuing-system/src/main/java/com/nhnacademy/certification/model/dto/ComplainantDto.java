package com.nhnacademy.certification.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ComplainantDto {
    private String name;
    private String residentRegistrationNumber;
    private String birthReportQualificationsCode;
    private String deathReportQualificationsCode;
    private String emailAddress;
    private String phoneNumber;


    public ComplainantDto(String name, String residentRegistrationNumber, String birthReportQualificationsCode,
                          String deathReportQualificationsCode, String emailAddress, String phoneNumber) {
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.birthReportQualificationsCode = birthReportQualificationsCode;
        this.deathReportQualificationsCode = deathReportQualificationsCode;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }
}
