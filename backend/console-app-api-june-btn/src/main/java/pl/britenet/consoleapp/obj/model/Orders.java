package pl.britenet.consoleapp.obj.model;

import pl.britenet.consoleapp.Constants;

import java.time.LocalDate;

public class Orders {
    private final int id;
    private int UsersId;
    private LocalDate date;
    private String status;
    private String address;
    private double totalPrice;

    public Orders(int id){
        this.id = id;
    }

    public Orders(){
        this.id = Constants.INVALID_ID;
    }

    public int getId() {
        return id;
    }

    public int getUsersId() {
        return UsersId;
    }

    public void setUsersId(int usersId) {
        UsersId = usersId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
