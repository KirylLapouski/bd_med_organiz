package entity;

/**
 * Created by lapko on 16.11.2017.
 */
public class LaboratorySpecEntity {
    private int id;
    private String name;

    public LaboratorySpecEntity() {
    }

    public LaboratorySpecEntity(String name) {
        this.name = name;
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
}
