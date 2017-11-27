package entity;

import java.util.List;
import java.util.Set;

/**
 * Created by lapko on 10.11.2017.
 */
//DONE
public class HousingEntity {
    private int id;
    private String name;
    private String address;
    private Set departments;
    private MedicalFacilityEntity medicalFacility;
    public HousingEntity() {
    }

    public HousingEntity(String name, String address, Set departments, MedicalFacilityEntity medicalFacilityEntity) {
        this.name = name;
        this.address = address;
        this.departments = departments;
        this.medicalFacility = medicalFacilityEntity;
    }

    public MedicalFacilityEntity getMedicalFacility() {
        return medicalFacility;
    }

    public void setMedicalFacility(MedicalFacilityEntity medicalFacility) {
        this.medicalFacility = medicalFacility;
    }

    public Set getDepartments() {

        return departments;
    }

    public void setDepartments(Set departments) {
        this.departments = departments;
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

        HousingEntity that = (HousingEntity) o;

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
