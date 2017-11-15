package entity;

/**
 * Created by lapko on 16.11.2017.
 */
public class TypeOfAnalysisEntity {
    private int id;
    private String name;

    public TypeOfAnalysisEntity(String name) {
        this.name = name;
    }

    public TypeOfAnalysisEntity() {
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
