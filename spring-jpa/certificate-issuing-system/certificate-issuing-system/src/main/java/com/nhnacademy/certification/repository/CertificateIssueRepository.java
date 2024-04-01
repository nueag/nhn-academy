package com.nhnacademy.certification.repository;

import com.nhnacademy.certification.model.CertificateIssue;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {
    @Query("select c.certificateTypeCode from CertificateIssue c where c.residentSerialNumber = ?1")
    List<String> findAllByResidentSerialNumber(int residentNumber);

    CertificateIssue findAllByResidentSerialNumberAndCertificateTypeCode(int residentId, String certificateType);

}
