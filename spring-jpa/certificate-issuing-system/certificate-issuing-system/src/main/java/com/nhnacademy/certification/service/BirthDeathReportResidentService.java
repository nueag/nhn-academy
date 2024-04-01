package com.nhnacademy.certification.service;

import com.nhnacademy.certification.model.BirthDeathReportResident;
import com.nhnacademy.certification.model.request.BirthDeathReportResidentRequest;

public interface BirthDeathReportResidentService {
    BirthDeathReportResident registerBirthReport(int residentSerialNumber, BirthDeathReportResidentRequest request);

    BirthDeathReportResident updateBirthReport(int residentSerialNumber, int targetSerialNumber, BirthDeathReportResidentRequest request);

    void deleteBirthReport(int residentSerialNumber, int targetSerialNumber, String birthDeathType);

    BirthDeathReportResident registerDeathReport(int residentSerialNumber, BirthDeathReportResidentRequest request);

    BirthDeathReportResident updateDeathReport(int residentSerialNumber, int targetSerialNumber, BirthDeathReportResidentRequest request);

    void deleteDeathReport(int residentSerialNumber, int targetSerialNumber, String birthDeathType);
}
