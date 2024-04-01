package com.nhnacademy.certification.controller;

import com.nhnacademy.certification.model.dto.FamilyRelationshipCertificateDto;
import com.nhnacademy.certification.model.dto.FamilyRelationshipDto;
import com.nhnacademy.certification.repository.ResidentRepository;
import com.nhnacademy.certification.service.ResidentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FamilyRelationshipCertificateController {
    private ResidentService residentService;

    @Autowired
    public FamilyRelationshipCertificateController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping("/familyRelationshipCertificate/{id}")
    public String index(@PathVariable("id") int residentSerialNumber,
                        Model model) {
        FamilyRelationshipCertificateDto familyRelationshipCertificate =
                residentService.getFamilyRelationshipCertificate(residentSerialNumber);
        model.addAttribute("familyRelationCertificate", familyRelationshipCertificate);
        return "thymeleaf/certification/familyRelationCertificate";
    }

}
