package com.nhnacademy.certification.model.request;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BirthDeathReportResidentRequest {
    @NotNull
    private int residentSerialNumber;
    @NotNull
    private String birthDeathTypeCode;
    @NotNull
    private int reportResidentSerialNumber;
    @NotNull
    private String birthDeathReportDate;
    private String birthReportQualificationsCode;
    private String deathReportQualificationsCode;
    private String emailAddress;
    @NotNull
    private String phoneNumber;
}
