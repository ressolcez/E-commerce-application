package pl.britenet.consoleapp.obj.model;

public class UserMostOrders {
    private final int usersId;
    private String name;
    private String surname;
    private int numberOfOrders;

    public UserMostOrders(int usersId){
        this.usersId = usersId;
    }

    public int getUsersId() {
        return usersId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }
}
