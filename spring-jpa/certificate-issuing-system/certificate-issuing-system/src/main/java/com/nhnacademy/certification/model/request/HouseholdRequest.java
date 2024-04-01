package com.nhnacademy.certification.model.request;

import com.nhnacademy.certification.model.Resident;
import java.time.LocalDateTime;
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
public class HouseholdRequest {
    @NotNull
    int householdSerialNumber;
    @NotNull
    int householdResidentSerialNumber;
    @NotNull
    String householdCompositionDate;
    @NotNull
    String householdCompositionReasonCode;
    @NotNull
    String currentHouseMovementAddress;

}
