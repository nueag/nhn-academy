package com.nhnacademy.certification.model.request;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdMovementRequest {
    @NotNull
    private int householdSerialNumber;
    @NotNull
    private String houseMovementReportDate;
    @NotNull
    private String houseMovementAddress;
    @NotNull
    private String lastAddressYn;
}
