package com.nhnacademy.certification.model.request;

import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
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
public class ResidentRequest {
    @NotNull
    private int residentSerialNumber;
    @NotNull
    private String name;
    @NotNull
    private String residentRegistrationNumber;
    @NotNull
    private String genderCode;
    @NotNull
    private String birthDate;

    @NotNull
    private String birthPlaceCode;
    @NotNull
    private String registrationBaseAddress;
    private String deathDate;

    private String deathPlaceCode;

    private String deathPlaceAddress;

}
