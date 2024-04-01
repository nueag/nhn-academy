package com.nhnacademy.certification.controller.rest;

import com.nhnacademy.certification.model.Household;
import com.nhnacademy.certification.model.request.HouseholdRequest;
import com.nhnacademy.certification.service.FamilyRelationshipService;
import com.nhnacademy.certification.service.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HouseholdController {
    private HouseholdService householdService;

    @Autowired
    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @PostMapping("/household")
    public ResponseEntity<Household> registerHousehold(@RequestBody HouseholdRequest householdRequest) {
        Household household = householdService.registerHousehold(householdRequest);
        return ResponseEntity.ok()
                .body(household);
    }

    @DeleteMapping("/household/{householdSerialNumber}")
    public ResponseEntity<Household> registerHousehold(@PathVariable("householdSerialNumber") int householdSerialNumber) {
        householdService.deleteHousehold(householdSerialNumber);
        return ResponseEntity.ok()
                .build();
    }
}
