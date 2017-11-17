package entity;

/**
 * Created by lapko on 10.11.2017.
 */
//DONE
public class AnalysisEntity {
    private int id;
    TypeOfAnalysisEntity typeOfAnalys;

    public AnalysisEntity( TypeOfAnalysisEntity typeOfAnalys) {
        this.typeOfAnalys = typeOfAnalys;
    }

    public AnalysisEntity() {
    }

    public TypeOfAnalysisEntity getTypeOfAnalys() {
        return typeOfAnalys;
    }

    public void setTypeOfAnalys(TypeOfAnalysisEntity typeOfAnalys) {
        this.typeOfAnalys = typeOfAnalys;
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

        AnalysisEntity that = (AnalysisEntity) o;

        if (id != that.id) return false;
        return !(typeOfAnalys != null ? !typeOfAnalys.equals(that.typeOfAnalys) : that.typeOfAnalys != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (typeOfAnalys != null ? typeOfAnalys.hashCode() : 0);
        return result;
    }
}
