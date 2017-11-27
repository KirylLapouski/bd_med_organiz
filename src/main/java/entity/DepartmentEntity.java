package entity;

import javax.swing.event.ListDataEvent;
import java.util.List;
import java.util.Set;

/**
 * Created by lapko on 10.11.2017.
 */
//DONE
public class DepartmentEntity {
    private int id;
    private String name;
    private Set rooms;
    private Set offices;
    private Set departmentSpetialization;
    private HousingEntity housing;

    public DepartmentEntity() {
    }


    public DepartmentEntity(String name, Set rooms, Set offices, Set departmentSpetialization, HousingEntity housing) {
        this.name = name;
        this.rooms = rooms;
        this.offices = offices;
        this.departmentSpetialization = departmentSpetialization;
        this.housing  =housing;
    }

    public HousingEntity getHousing() {
        return housing;
    }

    public void setHousing(HousingEntity housing) {
        this.housing = housing;
    }

    public Set getRooms() {
        return rooms;
    }

    public void setRooms(Set rooms) {
        this.rooms = rooms;
    }

    public Set getOffices() {
        return offices;
    }

    public void setOffices(Set offices) {
        this.offices = offices;
    }

    public Set getDepartmentSpetialization() {
        return departmentSpetialization;
    }

    public void setDepartmentSpetialization(Set departmentSpetialization) {
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
