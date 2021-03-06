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
    private StaffEntity staff;
    private Date to;
    private Date since;
    private String rate;
    private String typeOfWork;

    public JobEntity(MedicalFacilityEntity medicalFacility, PositionEntity position, DepartmentEntity department, OfficeEntity office, Date to, Date since, String rate, String typeOfWork) {
        this.medicalFacility = medicalFacility;
        this.position = position;
        this.department = department;
        this.office = office;
        this.to = to;
        this.since = since;
        this.rate = rate;
        this.typeOfWork = typeOfWork;
    }

    public StaffEntity getStaff() {
        return staff;
    }

    public void setStaff(StaffEntity staff) {
        this.staff = staff;
    }

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

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
