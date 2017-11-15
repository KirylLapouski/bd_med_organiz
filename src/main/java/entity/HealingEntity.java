package entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by lapko on 11.11.2017.
 */
public class HealingEntity {
    private MedicalFacilityEntity medicalFacility;
    private Date to;
    private OccupiedBedsEntity bed;
    private StaffEntity doctor;


    public MedicalFacilityEntity getMedicalFacility() {
        return medicalFacility;
    }

    public void setMedicalFacility(MedicalFacilityEntity medicalFacility) {
        this.medicalFacility = medicalFacility;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to =new Date(Integer.parseInt(to.toString())) ;
    }

    public OccupiedBedsEntity getBed() {
        return bed;
    }

    public void setBed(OccupiedBedsEntity bed) {
        this.bed = bed;
    }

    public StaffEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(StaffEntity doctor) {
        this.doctor = doctor;
    }

    public HealingEntity() {

    }

    public HealingEntity(MedicalFacilityEntity medicalFacility, Date to, OccupiedBedsEntity bed, StaffEntity doctor) {

        this.medicalFacility = medicalFacility;
        this.to = to;
        this.bed = bed;
        this.doctor = doctor;
    }
}
