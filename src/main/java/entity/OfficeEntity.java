package entity;

/**
 * Created by lapko on 10.11.2017.
 */
//DONE
public class OfficeEntity {
    private int id;
    private StaffEntity doctor;
    private DepartmentEntity department;
    public OfficeEntity() {
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public OfficeEntity(StaffEntity doctor, DepartmentEntity department) {
        this.doctor = doctor;
        this.department = department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public StaffEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(StaffEntity doctor) {
        this.doctor = doctor;
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

        OfficeEntity that = (OfficeEntity) o;

        if (id != that.id) return false;
        if (doctor != null ? !doctor.equals(that.doctor) : that.doctor != null) return false;
        return !(department != null ? !department.equals(that.department) : that.department != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (doctor != null ? doctor.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        return result;
    }
}
