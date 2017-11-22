package entity;

/**
 * Created by lapko on 10.11.2017.
 */
//DONE
public class SpecialtyEntity {
    public static enum Degree{candidateOfMedicalSciences,doctorOfMedicalSciences};
    public static enum Grade{associateProfessor,Professor};


    private int id;
    private String name;
    private Boolean isDoctor;
    private float salary;
    private String degree;
    private String grade;

    public SpecialtyEntity() {
    }

    public SpecialtyEntity(String name, Boolean isDoctor, float salary, String degree, String grade) {
        this.name = name;
        this.isDoctor = isDoctor;
        this.salary = salary;
        this.degree = degree;
        this.grade = grade;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Boolean getIsDoctor() {

        return isDoctor;
    }

    public void setIsDoctor(Boolean isDoctor) {
        this.isDoctor = isDoctor;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecialtyEntity that = (SpecialtyEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SpecialtyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDoctor=" + isDoctor +
                ", salary=" + salary +
                ", degree=" + degree +
                ", grade=" + grade +
                '}';
    }
}
