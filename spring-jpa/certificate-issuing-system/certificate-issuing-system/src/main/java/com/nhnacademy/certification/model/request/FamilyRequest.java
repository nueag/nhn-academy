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
public class FamilyRequest {
    @NotNull
    private int baseResidentSerialNumber;

    @NotNull
    private int familyResidentSerialNumber;

    @NotNull
    private String familyRelationshipCode;
}