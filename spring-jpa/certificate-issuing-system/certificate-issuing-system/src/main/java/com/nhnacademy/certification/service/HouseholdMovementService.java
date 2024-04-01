package com.nhnacademy.certification.service;

import com.nhnacademy.certification.model.HouseholdMovementAddress;
import com.nhnacademy.certification.model.request.HouseholdMovementRequest;

public interface HouseholdMovementService {
    HouseholdMovementAddress registerHouseholdMovement(int householdSerialNumber, HouseholdMovementRequest request);

    HouseholdMovementAddress updateHouseholdMovement(int householdSerialNumber, String reportDate,
                                                     HouseholdMovementRequest request);

    void deleteHouseholdMovement(int householdSerialNumber, String reportDate);
}
