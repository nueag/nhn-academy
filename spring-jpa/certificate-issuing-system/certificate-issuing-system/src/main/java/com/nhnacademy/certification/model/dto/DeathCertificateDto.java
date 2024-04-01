package com.nhnacademy.certification.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeathCertificateDto {
    private String certificateIssueDate;
    private String registrationBaseAddress;
    private BirthDeathInfoDto birthDeathInfoDto;
    private ComplainantDto complainantDto;

}