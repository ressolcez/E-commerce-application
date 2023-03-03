package pl.britenet.consoleapp.obj.model;

import java.time.LocalDate;

public class OrdersWithAddress {

    private final int ordersId;
    private String address;
    private LocalDate date;

    public OrdersWithAddress(int ordersId){
        this.ordersId = ordersId;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
