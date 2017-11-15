package entity;


import java.util.List;
import java.util.Set;

/**
 * Created by lapko on 10.11.2017.
 */
//DONE
public class RoomEntity {
    private int id;
    private int roomNumber;
    private Integer numberOfBeds;
    private DepartmentEntity department;
    private StaffEntity ResponsibleDoctor;
    private Set beds;

    public RoomEntity() {
    }

    public RoomEntity(int roomNumber, Integer numberOfBeds, DepartmentEntity department, StaffEntity responsibleDoctor, Set beds) {
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
        this.department = department;
        ResponsibleDoctor = responsibleDoctor;
        this.beds = beds;
    }

    public Set getBeds() {
        return beds;
    }

    public void setBeds(Set beds) {
        this.beds = beds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(Integer numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public StaffEntity getResponsibleDoctor() {
        return ResponsibleDoctor;
    }

    public void setResponsibleDoctor(StaffEntity responsibleDoctor) {
        ResponsibleDoctor = responsibleDoctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomEntity that = (RoomEntity) o;

        if (id != that.id) return false;
        if (roomNumber != that.roomNumber) return false;
        if (numberOfBeds != null ? !numberOfBeds.equals(that.numberOfBeds) : that.numberOfBeds != null) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (ResponsibleDoctor != null ? !ResponsibleDoctor.equals(that.ResponsibleDoctor) : that.ResponsibleDoctor != null)
            return false;
        return !(beds != null ? !beds.equals(that.beds) : that.beds != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + roomNumber;
        result = 31 * result + (numberOfBeds != null ? numberOfBeds.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (ResponsibleDoctor != null ? ResponsibleDoctor.hashCode() : 0);
        result = 31 * result + (beds != null ? beds.hashCode() : 0);
        return result;
    }
}
