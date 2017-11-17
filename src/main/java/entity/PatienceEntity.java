package entity;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lapko on 10.11.2017.
 */
//DONE
public class PatienceEntity {
    private int id;
    private String fio;
    private Set disease;
    private Set analysis;

    public PatienceEntity() {
    }

    public PatienceEntity(String fio, Set disease, Set analysis, Map<Date, HealingEntity> arrival) {
        this.fio = fio;
        this.disease = disease;
        this.analysis = analysis;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Set getDisease() {
        return disease;
    }

    public void setDisease(Set disease) {
        this.disease = disease;
    }

    public Set getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Set analysis) {
        this.analysis = analysis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatienceEntity that = (PatienceEntity) o;

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
