package com.nhnacademy.certification.controller.rest;

import com.nhnacademy.certification.model.BirthDeathReportResident;
import com.nhnacademy.certification.model.dto.FamilyRelationshipCertificateDto;
import com.nhnacademy.certification.model.request.BirthDeathReportResidentRequest;
import com.nhnacademy.certification.service.BirthDeathReportResidentService;
import com.nhnacademy.certification.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BirthRestController {
    private BirthDeathReportResidentService birthDeathReportResidentService;

    @Autowired
    public BirthRestController(BirthDeathReportResidentService birthDeathReportResidentService) {
        this.birthDeathReportResidentService = birthDeathReportResidentService;
    }

    @PostMapping("/residents/{serialNumber}/birth")
    public ResponseEntity<BirthDeathReportResident> registerBirth(@PathVariable("serialNumber") int residentSerialNumber,
                                                          @RequestBody BirthDeathReportResidentRequest request) {
        return ResponseEntity.ok()
                .body(birthDeathReportResidentService.registerBirthReport(residentSerialNumber, request));
    }

    @PutMapping("/residents/{serialNumber}/birth/{targetSerialNumber}")
    public ResponseEntity<BirthDeathReportResident> updateBirth(@PathVariable("serialNumber") int residentSerialNumber,
                                                                @PathVariable("targetSerialNumber") int targetSerialNumber,
                                                                  @RequestBody BirthDeathReportResidentRequest request) {
        return ResponseEntity.ok()
                .body(birthDeathReportResidentService.updateBirthReport(residentSerialNumber, targetSerialNumber, request));
    }

    @DeleteMapping("/residents/{serialNumber}/birth/{targetSerialNumber}")
    public void deleterBirth(@PathVariable("serialNumber") int residentSerialNumber,
                                                                 @PathVariable("targetSerialNumber") int targetSerialNumber) {
        birthDeathReportResidentService.deleteBirthReport(residentSerialNumber, targetSerialNumber, "출생");

    }

}