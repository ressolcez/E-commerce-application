package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.obj.model.OrdersWithAddress;
import pl.britenet.consoleapp.obj.model.UserMostOrders;


public class UsersMostOrdersBuilder {
    private final UserMostOrders userMostOrders;

    public UsersMostOrdersBuilder(int usersId) {
        this.userMostOrders = new UserMostOrders(usersId);
    }

    public UsersMostOrdersBuilder setName(String name) {
        this.userMostOrders.setName(name);
        return this;
    }

    public UsersMostOrdersBuilder setSurname(String surname) {
        this.userMostOrders.setSurname(surname);
        return this;
    }

    public UsersMostOrdersBuilder setNumberOfOrders(int numberOfOrders) {
        this.userMostOrders.setNumberOfOrders(numberOfOrders);
        return this;
    }

    public UserMostOrders getUserMostOrders (){
        return this.userMostOrders;
    }



}
