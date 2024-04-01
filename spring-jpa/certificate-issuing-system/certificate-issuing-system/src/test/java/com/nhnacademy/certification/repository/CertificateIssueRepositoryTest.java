package com.nhnacademy.certification.repository;

import com.nhnacademy.certification.config.RootConfig;
import com.nhnacademy.certification.config.WebConfig;
import com.nhnacademy.certification.model.CertificateIssue;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class CertificateIssueRepositoryTest {
    @Autowired
    private CertificateIssueRepository certificateIssueRepository;


    @Test
    void findAllByResidentId() {
        List<String> certificateIssueList = certificateIssueRepository.findAllByResidentSerialNumber(4);

        Assertions.assertThat(certificateIssueList.size()).isEqualTo(2);
    }

    @Test
    void findAllByResidentSerialNumberAndCertificateTypeCodeTest() {
        CertificateIssue certificateIssue =
                certificateIssueRepository.findAllByResidentSerialNumberAndCertificateTypeCode(4, "주민등록등본");
        Assertions.assertThat(certificateIssue.getCertificateConfirmationNumber()).isEqualTo(9876543210987654L);
    }


}