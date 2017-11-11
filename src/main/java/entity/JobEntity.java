package entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by lapko on 11.11.2017.
 */
//DONE
public class JobEntity {
    private MedicalFacilityEntity medicalFacility;
    private PositionEntity position;
    private DepartmentEntity department;
    private OfficeEntity office;
    private Date to;

    private String rate;
    private String typeOfWork;

    public Date getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to = new Date(Integer.parseInt(to.toString())) ;
    }

    public OfficeEntity getOffice() {
        return office;
    }

    public void setOffice(OfficeEntity office) {
        this.office = office;
    }

    public MedicalFacilityEntity getMedicalFacility() {
        return medicalFacility;
    }

    public void setMedicalFacility(MedicalFacilityEntity medicalFacility) {
        this.medicalFacility = medicalFacility;
    }

    public PositionEntity getPosition() {
        return position;
    }

    public void setPosition(PositionEntity position) {
        this.position = position;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public JobEntity() {
    }

    public JobEntity(MedicalFacilityEntity medicalFacility, PositionEntity position, DepartmentEntity department, String rate, String typeOfWork) {

        this.medicalFacility = medicalFacility;
        this.position = position;
        this.department = department;
        this.rate = rate;
        this.typeOfWork = typeOfWork;
    }
}
