package entity.primaryKey;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by lapko on 10.11.2017.
 */
public class OperationsEntityPK implements Serializable {
    private int idStaff;
    private int idPatience;
    private int idDisease;
    private Timestamp since;

    public int getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }

    public int getIdPatience() {
        return idPatience;
    }

    public void setIdPatience(int idPatience) {
        this.idPatience = idPatience;
    }

    public int getIdDisease() {
        return idDisease;
    }

    public void setIdDisease(int idDisease) {
        this.idDisease = idDisease;
    }

    public Timestamp getSince() {
        return since;
    }

    public void setSince(Timestamp since) {
        this.since = since;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationsEntityPK that = (OperationsEntityPK) o;

        if (idStaff != that.idStaff) return false;
        if (idPatience != that.idPatience) return false;
        if (idDisease != that.idDisease) return false;
        if (since != null ? !since.equals(that.since) : that.since != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idStaff;
        result = 31 * result + idPatience;
        result = 31 * result + idDisease;
        result = 31 * result + (since != null ? since.hashCode() : 0);
        return result;
    }
}
