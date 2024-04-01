package com.nhnacademy.certification.repository.Impl;

import com.nhnacademy.certification.model.QFamilyRelationship;
import com.nhnacademy.certification.model.QResident;
import com.nhnacademy.certification.model.Resident;
import com.nhnacademy.certification.model.dto.FamilyRelationshipDto;
import com.nhnacademy.certification.model.dto.ParentInfoDto;
import com.nhnacademy.certification.repository.custom.ResidentRepositoryCustom;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ResidentRepositoryImpl extends QuerydslRepositorySupport implements ResidentRepositoryCustom {
    public ResidentRepositoryImpl() {
        super(Resident.class);
    }

    @Override
    public List<FamilyRelationshipDto> getFamilyRelationshipList(int residentId) {
        QResident resident = QResident.resident;
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;


        return from(familyRelationship)
                .where(resident.residentSerialNumber.in(
                        from(familyRelationship)
                                .leftJoin(familyRelationship.resident, resident)
                                .where(familyRelationship.pk.baseResidentSerialNumber.eq(residentId))
                                .select(familyRelationship.pk.familyResidentSerialNumber)))
                .leftJoin(familyRelationship.resident, resident)
                .where(familyRelationship.pk.baseResidentSerialNumber.eq(residentId))
                .select(Projections.constructor(FamilyRelationshipDto.class,
                        familyRelationship.familyRelationshipCode,
                        resident.name,
                        resident.birthDate,
                        resident.residentRegistrationNumber,
                        resident.genderCode))
                .distinct()
                .fetch();
    }

    @Override
    public List<ParentInfoDto> getParentInfoList(int residentSerialNumber) {
        QResident resident = QResident.resident;
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;

        return from(familyRelationship)
                .where(resident.residentSerialNumber.in(
                        from(familyRelationship)
                                .leftJoin(familyRelationship.resident, resident)
                                .where(familyRelationship.pk.baseResidentSerialNumber.eq(residentSerialNumber)
                                        .and((familyRelationship.familyRelationshipCode.eq("부")
                                                .or(familyRelationship.familyRelationshipCode.eq("모")))
                                        ))
                                .select(familyRelationship.pk.familyResidentSerialNumber)))
                .where(familyRelationship.pk.baseResidentSerialNumber.eq(residentSerialNumber))
                .select(Projections.constructor(ParentInfoDto.class,
                        familyRelationship.familyRelationshipCode,
                        resident.name,
                        resident.residentRegistrationNumber))
                .fetch();
    }
}
