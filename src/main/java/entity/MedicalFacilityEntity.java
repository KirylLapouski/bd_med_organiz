package entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lapko on 10.11.2017.
 */
//DONE
public class MedicalFacilityEntity {
    private int id;
    private String name;
    private String address;
    private String medicalFacilityType;
    private MedicalFacilityEntity superior_hospital;
    private Map<Date,StaffEntity> orderForHospitalDoctor;
    private Map<HousingEntity,DepartmentEntity> departmentsLocation;

    public List<LaboratoryEntity> getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(List<LaboratoryEntity> laboratory) {
        this.laboratory = laboratory;
    }

    private List<LaboratoryEntity> laboratory;

    public Map<HousingEntity, DepartmentEntity> getDepartmentsLocation() {
        return departmentsLocation;
    }

    public void setDepartmentsLocation(Map<HousingEntity, DepartmentEntity> departmentsLocation) {
        this.departmentsLocation = departmentsLocation;
    }

    public MedicalFacilityEntity getSuperior_hospital() {
        return superior_hospital;
    }

    public void setSuperior_hospital(MedicalFacilityEntity superior_hospital) {
        this.superior_hospital = superior_hospital;
    }

    public Map<Date, StaffEntity> getOrderForHospitalDoctor() {
        return orderForHospitalDoctor;
    }

    public void setOrderForHospitalDoctor(Map<Date, StaffEntity> orderForHospitalDoctor) {
        this.orderForHospitalDoctor = orderForHospitalDoctor;
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

    public String getMedicalFacilityType() {
        return medicalFacilityType;
    }

    public void setMedicalFacilityType(String medicalFacilityType) {
        this.medicalFacilityType = medicalFacilityType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicalFacilityEntity that = (MedicalFacilityEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (medicalFacilityType != null ? !medicalFacilityType.equals(that.medicalFacilityType) : that.medicalFacilityType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (medicalFacilityType != null ? medicalFacilityType.hashCode() : 0);
        return result;
    }
}
