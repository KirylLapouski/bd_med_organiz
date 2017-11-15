package entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by lapko on 10.11.2017.
 */
//DONE
public class OccupiedBedsEntity {
    private int id;
    private Date since;
    private Date to;
    private int id_room;

    public OccupiedBedsEntity(Date since, Date to, int id_room) {
        this.since = since;
        this.to = to;
        this.id_room = id_room;
    }

    public OccupiedBedsEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public int getId_room() {
        return id_room;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OccupiedBedsEntity that = (OccupiedBedsEntity) o;

        if (id != that.id) return false;
        if (since != null ? !since.equals(that.since) : that.since != null) return false;
        if (to != null ? !to.equals(that.to) : that.to != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (since != null ? since.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }
}
