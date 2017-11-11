package entity.primaryKey;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by lapko on 10.11.2017.
 */
public class AppointmentEntityPK implements Serializable {
    private int idDoctor;
    private int idPatience;
    private Timestamp since;
    private Timestamp to;

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public int getIdPatience() {
        return idPatience;
    }

    public void setIdPatience(int idPatience) {
        this.idPatience = idPatience;
    }

    public Timestamp getSince() {
        return since;
    }

    public void setSince(Timestamp since) {
        this.since = since;
    }

    public Timestamp getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppointmentEntityPK that = (AppointmentEntityPK) o;

        if (idDoctor != that.idDoctor) return false;
        if (idPatience != that.idPatience) return false;
        if (since != null ? !since.equals(that.since) : that.since != null) return false;
        if (to != null ? !to.equals(that.to) : that.to != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDoctor;
        result = 31 * result + idPatience;
        result = 31 * result + (since != null ? since.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }
}
