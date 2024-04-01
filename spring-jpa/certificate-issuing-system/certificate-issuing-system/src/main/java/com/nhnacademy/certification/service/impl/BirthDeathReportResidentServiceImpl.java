package com.nhnacademy.certification.service.impl;

import com.nhnacademy.certification.model.BirthDeathReportResident;
import com.nhnacademy.certification.model.Resident;
import com.nhnacademy.certification.model.request.BirthDeathReportResidentRequest;
import com.nhnacademy.certification.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.certification.repository.ResidentRepository;
import com.nhnacademy.certification.service.BirthDeathReportResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BirthDeathReportResidentServiceImpl implements BirthDeathReportResidentService {
    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;
    private final ResidentRepository residentRepository;

    @Autowired
    public BirthDeathReportResidentServiceImpl(BirthDeathReportResidentRepository birthDeathReportResidentRepository,
                                               ResidentRepository residentRepository) {
        this.birthDeathReportResidentRepository = birthDeathReportResidentRepository;
        this.residentRepository = residentRepository;
    }

    @Override
    public BirthDeathReportResident registerBirthReport(int residentSerialNumber,
                                                        BirthDeathReportResidentRequest request) {
        Resident resident = residentRepository.findById(residentSerialNumber).get();

        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setResidentSerialNumber(residentSerialNumber);
        pk.setBirthDeathTypeCode(request.getBirthDeathTypeCode());
        pk.setReportResidentSerialNumber(request.getReportResidentSerialNumber());

        BirthDeathReportResident birthDeathReportResident =
                new BirthDeathReportResident(pk, request.getBirthDeathReportDate(),
                        request.getBirthReportQualificationsCode(), request.getDeathReportQualificationsCode(),
                        request.getEmailAddress(), request.getPhoneNumber(), resident);

        return birthDeathReportResidentRepository.save(birthDeathReportResident);
    }

    @Override
    public BirthDeathReportResident updateBirthReport(int residentSerialNumber, int targetSerialNumber,
                                                      BirthDeathReportResidentRequest request) {
        request.setReportResidentSerialNumber(residentSerialNumber);
        return registerBirthReport(targetSerialNumber, request);
    }

    @Override
    public void deleteBirthReport(int residentSerialNumber, int targetSerialNumber,
                                  String birthDeathType) {

        if (!birthDeathReportResidentRepository.existsById(
                new BirthDeathReportResident.Pk(targetSerialNumber, birthDeathType, residentSerialNumber))) {
            throw new RuntimeException("Family relationship not found");
        }
        birthDeathReportResidentRepository.deleteById(
                new BirthDeathReportResident.Pk(targetSerialNumber, birthDeathType, residentSerialNumber));


    }

    @Override
    public BirthDeathReportResident registerDeathReport(int residentSerialNumber,
                                                        BirthDeathReportResidentRequest request) {
        Resident resident = residentRepository.findById(residentSerialNumber).get();

        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setResidentSerialNumber(residentSerialNumber);
        pk.setBirthDeathTypeCode(request.getBirthDeathTypeCode());
        pk.setReportResidentSerialNumber(request.getReportResidentSerialNumber());

        BirthDeathReportResident birthDeathReportResident =
                new BirthDeathReportResident(pk, request.getBirthDeathReportDate(),
                        request.getBirthReportQualificationsCode(), request.getDeathReportQualificationsCode(),
                        request.getEmailAddress(), request.getPhoneNumber(), resident);

        return birthDeathReportResidentRepository.save(birthDeathReportResident);

    }

    @Override
    public BirthDeathReportResident updateDeathReport(int residentSerialNumber, int targetSerialNumber,
                                                      BirthDeathReportResidentRequest request) {
        request.setReportResidentSerialNumber(residentSerialNumber);
        return registerDeathReport(targetSerialNumber, request);
    }

    @Override
    public void deleteDeathReport(int residentSerialNumber, int targetSerialNumber, String birthDeathType) {
        if (!birthDeathReportResidentRepository.existsById(
                new BirthDeathReportResident.Pk(targetSerialNumber, birthDeathType, residentSerialNumber))) {
            throw new RuntimeException("Family relationship not found");
        }
        birthDeathReportResidentRepository.deleteById(
                new BirthDeathReportResident.Pk(targetSerialNumber, birthDeathType, residentSerialNumber));

    }
}
