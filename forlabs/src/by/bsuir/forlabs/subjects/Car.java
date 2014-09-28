package by.bsuir.forlabs.subjects;

public class Car extends Entity {

    private int id;
    private String number;
    private boolean isFree;
    private int idSpecification;

    public Car () {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }

    public int getIdSpecification() {
        return idSpecification;
    }

    public void setIdSpecification(int idSpecification) {
        this.idSpecification = idSpecification;
    }
}
