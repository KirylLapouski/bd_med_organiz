package entity;

import java.util.List;

/**
 * Created by lapko on 10.11.2017.
 */
//DONE
public class RoomEntity {
    private int id;
    private int roomNumber;
    private Integer numberOfBeds;
    private int idResponsibleDoctor;
    private List<OccupiedBedsEntity> beds;


    public List<OccupiedBedsEntity> getBeds() {
        return beds;
    }

    public void setBeds(List<OccupiedBedsEntity> beds) {
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

    public int getIdResponsibleDoctor() {
        return idResponsibleDoctor;
    }

    public void setIdResponsibleDoctor(int idResponsibleDoctor) {
        this.idResponsibleDoctor = idResponsibleDoctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomEntity that = (RoomEntity) o;

        if (id != that.id) return false;
        if (roomNumber != that.roomNumber) return false;
        if (idResponsibleDoctor != that.idResponsibleDoctor) return false;
        if (numberOfBeds != null ? !numberOfBeds.equals(that.numberOfBeds) : that.numberOfBeds != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + roomNumber;
        result = 31 * result + (numberOfBeds != null ? numberOfBeds.hashCode() : 0);
        result = 31 * result + idResponsibleDoctor;
        return result;
    }
}
