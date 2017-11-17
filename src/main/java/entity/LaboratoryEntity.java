package entity;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lapko on 10.11.2017.
 */
//DONE
public class LaboratoryEntity {
    private int id;
    private String name;
    private String address;
    private Set specialization;
    private Set typeOfAnalysis;
    private Map<Date,MedicalFacilityEntity> medicalFacility;

    public LaboratoryEntity() {
    }

    public Map<Date, MedicalFacilityEntity> getMedicalFacility() {
        return medicalFacility;
    }

    public void setMedicalFacility(Map<Date, MedicalFacilityEntity> medicalFacility) {
        this.medicalFacility = medicalFacility;
    }


    public Set getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Set specialization) {
        this.specialization = specialization;
    }

    public Set getTypeOfAnalysis() {
        return typeOfAnalysis;
    }

    public void setTypeOfAnalysis(Set typeOfAnalysis) {
        this.typeOfAnalysis = typeOfAnalysis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LaboratoryEntity that = (LaboratoryEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
