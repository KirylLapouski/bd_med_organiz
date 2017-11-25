package entity;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lapko on 10.11.2017.
 */
//DONE
public class StaffEntity {
    private Integer id;
    private String fio;

    //FOR SpecialEntity take fields from staff_specialization
    private Set specializations; //SpecialtyEntity
    private Set shedule;//DAte



    public StaffEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setSpecializations(Set specializations) {
        this.specializations = specializations;
    }

    public Set getShedule() {
        return shedule;
    }

    public void setShedule(Set shedule) {
        this.shedule = shedule;
    }









    public Set getSpecializations() {
        return specializations;
    }

    public boolean isEmpty(){
        if(this.id == 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StaffEntity that = (StaffEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (fio != null ? !fio.equals(that.fio) : that.fio != null) return false;
        if (specializations != null ? !specializations.equals(that.specializations) : that.specializations != null)
            return false;
        return !(shedule != null ? !shedule.equals(that.shedule) : that.shedule != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + (specializations != null ? specializations.hashCode() : 0);
        result = 31 * result + (shedule != null ? shedule.hashCode() : 0);
        return result;
    }
}
