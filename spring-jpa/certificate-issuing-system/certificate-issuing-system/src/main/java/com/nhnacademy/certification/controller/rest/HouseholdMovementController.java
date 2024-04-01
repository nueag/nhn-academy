package com.nhnacademy.certification.controller.rest;

import com.nhnacademy.certification.model.Household;
import com.nhnacademy.certification.model.HouseholdMovementAddress;
import com.nhnacademy.certification.model.request.HouseholdMovementRequest;
import com.nhnacademy.certification.model.request.HouseholdRequest;
import com.nhnacademy.certification.service.HouseholdMovementService;
import com.nhnacademy.certification.service.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HouseholdMovementController {
    private HouseholdMovementService householdMovementService;

    @Autowired
    public HouseholdMovementController(HouseholdMovementService householdMovementService) {
        this.householdMovementService = householdMovementService;
    }
    @PostMapping("/household/{householdSerialNumber}/movement")
    public ResponseEntity<HouseholdMovementAddress> registerHousehold(@PathVariable("householdSerialNumber") int householdSerialNumber,
                                                                      @RequestBody HouseholdMovementRequest request) {
        HouseholdMovementAddress householdMovementAddress = householdMovementService.registerHouseholdMovement(householdSerialNumber, request);
        return ResponseEntity.ok()
                .body(householdMovementAddress);
    }

    @PutMapping("/household/{householdSerialNumber}/movement/{reportDate}")
    public ResponseEntity<HouseholdMovementAddress> updateHousehold(@PathVariable("householdSerialNumber") int householdSerialNumber,
                                                                      @PathVariable("reportDate") String reportDate,
                                                                      @RequestBody HouseholdMovementRequest request) {
        HouseholdMovementAddress householdMovementAddress = householdMovementService.updateHouseholdMovement(householdSerialNumber, reportDate, request);
        return ResponseEntity.ok()
                .body(householdMovementAddress);
    }


    @DeleteMapping("/household/{householdSerialNumber}/movement/{reportDate}")
    public ResponseEntity<HouseholdMovementAddress> deleteHousehold(@PathVariable("householdSerialNumber") int householdSerialNumber,
                                                                      @PathVariable("reportDate") String reportDate) {
        householdMovementService.deleteHouseholdMovement(householdSerialNumber, reportDate);
        return ResponseEntity.ok()
                .build();
    }

}
