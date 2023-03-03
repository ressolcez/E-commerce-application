package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Orders;

import java.time.LocalDate;

public class OrdersBuilder {
    private final Orders orders;

    public OrdersBuilder(int id) {this.orders = new Orders(id);}

    public OrdersBuilder(){
        this.orders = new Orders();
    }

    public OrdersBuilder setUsersId(int usersId) {
        this.orders.setUsersId(usersId);
        return this;
    }

    public OrdersBuilder setDate(LocalDate date) {
        this.orders.setDate(date);
        return this;
    }

    public OrdersBuilder setStatus(String status) {
        this.orders.setStatus(status);
        return this;
    }

    public OrdersBuilder setAddress(String address) {
        this.orders.setAddress(address);
        return this;
    }

    public OrdersBuilder setTotalPrice(double totalPrice) {
        this.orders.setTotalPrice(totalPrice);
        return this;
    }

    public Orders getOrders() {
        return this.orders;
    }
}
