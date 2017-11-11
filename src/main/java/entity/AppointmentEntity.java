package entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by lapko on 10.11.2017.
 */
//DONE
public class AppointmentEntity {
    private String complaints;
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
        this.to = new Date(Integer.parseInt(to.toString())) ;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppointmentEntity that = (AppointmentEntity) o;

        if (complaints != null ? !complaints.equals(that.complaints) : that.complaints != null) return false;
        if (doctor != null ? !doctor.equals(that.doctor) : that.doctor != null) return false;
        if (patience != null ? !patience.equals(that.patience) : that.patience != null) return false;
        if (disease != null ? !disease.equals(that.disease) : that.disease != null) return false;
        if (since != null ? !since.equals(that.since) : that.since != null) return false;
        return !(to != null ? !to.equals(that.to) : that.to != null);

    }

    @Override
    public int hashCode() {
        int result = complaints != null ? complaints.hashCode() : 0;
        result = 31 * result + (doctor != null ? doctor.hashCode() : 0);
        result = 31 * result + (patience != null ? patience.hashCode() : 0);
        result = 31 * result + (disease != null ? disease.hashCode() : 0);
        result = 31 * result + (since != null ? since.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }
}
