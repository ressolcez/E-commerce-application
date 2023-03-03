package pl.britenet.consoleapp.obj.model;

import pl.britenet.consoleapp.Constants;

public class Cart {
    private final int id;
    private int usersId;
    private double totalPrice;
    public Cart(int id){
        this.id = id;
    }
    public Cart(){
        this.id = Constants.INVALID_ID;
    }

    public int getId() {
        return id;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
