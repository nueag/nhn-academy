package com.nhnacademy.certification.controller.rest;

import com.nhnacademy.certification.model.BirthDeathReportResident;
import com.nhnacademy.certification.model.request.BirthDeathReportResidentRequest;
import com.nhnacademy.certification.service.BirthDeathReportResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeathRestController {
    private BirthDeathReportResidentService birthDeathReportResidentService;

    @Autowired
    public DeathRestController(BirthDeathReportResidentService birthDeathReportResidentService) {
        this.birthDeathReportResidentService = birthDeathReportResidentService;
    }

    @PostMapping("/residents/{serialNumber}/death")
    public ResponseEntity<BirthDeathReportResident> registerBirth(@PathVariable("serialNumber") int residentSerialNumber,
                                                                  @RequestBody BirthDeathReportResidentRequest request) {
        return ResponseEntity.ok()
                .body(birthDeathReportResidentService.registerDeathReport(residentSerialNumber, request));
    }

    @PutMapping("/residents/{serialNumber}/death/{targetSerialNumber}")
    public ResponseEntity<BirthDeathReportResident> updateBirth(@PathVariable("serialNumber") int residentSerialNumber,
                                                                @PathVariable("targetSerialNumber") int targetSerialNumber,
                                                                @RequestBody BirthDeathReportResidentRequest request) {
        return ResponseEntity.ok()
                .body(birthDeathReportResidentService.updateDeathReport(residentSerialNumber, targetSerialNumber, request));
    }

    @DeleteMapping("/residents/{serialNumber}/death/{targetSerialNumber}")
    public void deleterBirth(@PathVariable("serialNumber") int residentSerialNumber,
                             @PathVariable("targetSerialNumber") int targetSerialNumber) {
        birthDeathReportResidentService.deleteDeathReport(residentSerialNumber, targetSerialNumber, "사망");

    }

}
