package entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lapko on 10.11.2017.
 */
//DONE
public class StaffEntity {
    private int id;
    private String fio;
    private Map<Date, JobEntity> jobs;//!!!!!!!!!!!!!!!!!
    //!!!!!!!!!!!
    //FOR SpecialEntity take fields from staff_specialization
    private List<SpecialtyEntity> specializations;
    private List<Date> shedule;


    public Map<Date, JobEntity> getJobs() {
        return jobs;
    }

    public void setJobs(Map<Date, JobEntity> jobs) {
        this.jobs = jobs;
    }

    public List<SpecialtyEntity> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<SpecialtyEntity> specializations) {
        this.specializations = specializations;
    }

    public List<Date> getShedule() {
        return shedule;
    }

    public void setShedule(List<Date> shedule) {
        this.shedule = shedule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StaffEntity that = (StaffEntity) o;

        if (id != that.id) return false;
        if (fio != null ? !fio.equals(that.fio) : that.fio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        return result;
    }
}
