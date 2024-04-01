package com.nhnacademy.certification.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "family_relationship")
public class FamilyRelationship {
    @EmbeddedId
    private Pk pk;

    @NotNull
    @Column(name = "family_relationship_code")
    private String familyRelationshipCode;

    @MapsId("familyResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "family_resident_serial_number")
    private Resident resident;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    @Embeddable
    public static class Pk implements Serializable {

        @NotNull
        @Column(name = "base_resident_serial_number")
        private int baseResidentSerialNumber;

        @NotNull
        @Column(name = "family_resident_serial_number")
        private int familyResidentSerialNumber;
    }
}
