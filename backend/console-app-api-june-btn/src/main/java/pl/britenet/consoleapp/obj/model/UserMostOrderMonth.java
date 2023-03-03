package pl.britenet.consoleapp.obj.model;

public class UserMostOrderMonth {
    private final int usersId;
    private int month;
    private int numberOfOrders;

    public UserMostOrderMonth(int usersId){
        this.usersId = usersId;
    }

    public int getUsersId() {
        return usersId;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }
}
