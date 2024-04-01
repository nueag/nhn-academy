package com.nhnacademy.certification.service;

import com.nhnacademy.certification.model.Household;
import com.nhnacademy.certification.model.request.HouseholdRequest;

public interface HouseholdService {

    Household registerHousehold(HouseholdRequest householdRequest);

    void deleteHousehold(int householdSerialNumber);
}
