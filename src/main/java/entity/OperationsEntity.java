package entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by lapko on 10.11.2017.
 */
//DONE
public class OperationsEntity {
    private StaffEntity doctor;
    private PatienceEntity patience;
    private DiseaseEntity disease;
    private Date since;
    private Date to;


    public StaffEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(StaffEntity doctor) {
        this.doctor = doctor;
    }

    public PatienceEntity getPatience() {
        return patience;
    }

    public void setPatience(PatienceEntity patience) {
        this.patience = patience;
    }

    public DiseaseEntity getDisease() {
        return disease;
    }

    public void setDisease(DiseaseEntity disease) {
        this.disease = disease;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Timestamp since) {
        this.since = new Date(Integer.parseInt( since.toString()));
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to = new Date(Integer.parseInt( to.toString()));
    }

    public OperationsEntity() {

    }

    public OperationsEntity(StaffEntity doctor, PatienceEntity patience, DiseaseEntity disease, Date since, Date to) {

        this.doctor = doctor;
        this.patience = patience;
        this.disease = disease;
        this.since = since;
        this.to = to;
    }
}
