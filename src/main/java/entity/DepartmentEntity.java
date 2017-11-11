package entity;

import javax.swing.event.ListDataEvent;
import java.util.List;

/**
 * Created by lapko on 10.11.2017.
 */
//DONE
public class DepartmentEntity {
    private int id;
    private String name;
    private List<RoomEntity> rooms;
    private List<OfficeEntity> offices;
    private List<DiseaseEntity> departmentSpetialization;

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
    }

    public List<OfficeEntity> getOffices() {
        return offices;
    }

    public void setOffices(List<OfficeEntity> offices) {
        this.offices = offices;
    }

    public List<DiseaseEntity> getDepartmentSpetialization() {
        return departmentSpetialization;
    }

    public void setDepartmentSpetialization(List<DiseaseEntity> departmentSpetialization) {
        this.departmentSpetialization = departmentSpetialization;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentEntity that = (DepartmentEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
