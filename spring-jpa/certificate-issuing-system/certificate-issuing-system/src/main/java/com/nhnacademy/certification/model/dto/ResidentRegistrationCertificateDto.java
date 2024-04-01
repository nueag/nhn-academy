package com.nhnacademy.certification.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResidentRegistrationCertificateDto {
    private String certificateIssueDate;
    private long certificateConfirmationNumber;
    private String name;
    private String householdCompositionChangeReasonCode;
    private String reportDate;
    List<ResidentCertificateInfo> addressList = new ArrayList<>();
    List<CompositionInfoDto> compositionInfoDtoList = new ArrayList<>();

}
