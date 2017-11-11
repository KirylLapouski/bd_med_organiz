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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Timestamp since) {
        this.since = new Date( Integer.parseInt(since.toString()));
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to =new Date(Integer.parseInt(to.toString())) ;
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
