package com.nhnacademy.certification.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "certificate_issue")
public class CertificateIssue {
    @Id
    @NotNull
    @Column(name = "certificate_confirmation_number")
    private long certificateConfirmationNumber;

    @NotNull
    @Column(name = "resident_serial_number")
    private int residentSerialNumber;

    @NotNull
    @Column(name = "certificate_type_code")
    private String certificateTypeCode;

    @NotNull
    @Column(name = "certificate_issue_date")
    private String certificateIssueDate;

}

