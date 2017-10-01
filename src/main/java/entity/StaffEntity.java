package entity;

public class StaffEntity {
    private int id;
    private String fio;


    public StaffEntity(String fio) {
        this.fio = fio;
    }

    public StaffEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StaffEntity that = (StaffEntity) o;

        if (id != that.id) return false;
        if (fio != null ? !fio.equals(that.fio) : that.fio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "StaffEntity{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                '}';
    }

}
