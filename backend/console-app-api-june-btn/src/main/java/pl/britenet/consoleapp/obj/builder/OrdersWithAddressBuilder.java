package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.obj.model.OrdersWithAddress;

import java.time.LocalDate;

public class OrdersWithAddressBuilder {

    private final OrdersWithAddress ordersWithAddress;

    public OrdersWithAddressBuilder(int ordersId) {this.ordersWithAddress = new OrdersWithAddress(ordersId);}

    public OrdersWithAddressBuilder setDate(LocalDate date) {
        this.ordersWithAddress.setDate(date);
        return this;
    }

    public OrdersWithAddressBuilder setAddress(String address) {
        this.ordersWithAddress.setAddress(address);
        return this;
    }

    public OrdersWithAddress getOrdersWithAddress() {
        return this.ordersWithAddress;
    }

}
