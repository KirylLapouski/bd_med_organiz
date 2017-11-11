package entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lapko on 10.11.2017.
 */
//DONE
public class PatienceEntity {
    private int id;
    private String fio;
    private List<DiseaseEntity> disease;
    private List<AnalysisEntity> analysis;
    private Map<Date,HealingEntity> arrival;
    private List<OperationsEntity> operationsEntities;

    public List<OperationsEntity> getOperationsEntities() {
        return operationsEntities;
    }

    public void setOperationsEntities(List<OperationsEntity> operationsEntities) {
        this.operationsEntities = operationsEntities;
    }

    public Map<Date, HealingEntity> getArrival() {
        return arrival;
    }

    public void setArrival(Map<Date, HealingEntity> arrival) {
        this.arrival = arrival;
    }
    public List<AnalysisEntity> getAnalysis() {
        return analysis;
    }

    public void setAnalysis(List<AnalysisEntity> analysis) {
        this.analysis = analysis;
    }

    public List<DiseaseEntity> getDisease() {
        return disease;
    }

    public void setDisease(List<DiseaseEntity> disease) {
        this.disease = disease;
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
