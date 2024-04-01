package com.nhnacademy.certification.controller.rest;

import com.nhnacademy.certification.model.FamilyRelationship;
import com.nhnacademy.certification.model.request.FamilyRequest;
import com.nhnacademy.certification.service.FamilyRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FamilyRelationshipRestController {
    private FamilyRelationshipService familyRelationshipService;

    @Autowired
    public FamilyRelationshipRestController(FamilyRelationshipService familyRelationshipService) {
        this.familyRelationshipService = familyRelationshipService;
    }

    @PostMapping(value = "/residents/{serialNumber}/relationship", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FamilyRelationship> registerFamily(@PathVariable("serialNumber") int residentSerialNumber,
                                                             @RequestBody FamilyRequest familyRequest) {
//        try {
            FamilyRelationship familyRelationship =  familyRelationshipService.addFamilyRelationship(residentSerialNumber, familyRequest);
            return ResponseEntity.ok()
                    .body(familyRelationship);
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
    }

    @PutMapping(value = "/residents/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity<FamilyRelationship> modifyFamilyRelationship(@PathVariable("serialNumber") int residentSerialNumber,
                                                                       @PathVariable("familySerialNumber") int familySerialNumber,
                                                                       @RequestBody FamilyRequest familyRequest) {
        FamilyRelationship familyRelationship =  familyRelationshipService.updateFamilyRelationship(residentSerialNumber, familySerialNumber, familyRequest);
        return ResponseEntity.ok()
                .body(familyRelationship);
    }

    @DeleteMapping(value = "/residents/{serialNumber}/relationship/{familySerialNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FamilyRelationship> deleteFamilyRelationship(@PathVariable("serialNumber") int residentSerialNumber,
                                                                       @PathVariable("familySerialNumber") int familySerialNumber) {
        familyRelationshipService.deleteFamilyRelationship(residentSerialNumber, familySerialNumber);
        return ResponseEntity.ok()
                .build();
    }
}
