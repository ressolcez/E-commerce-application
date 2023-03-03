package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.obj.model.UserMostOrderMonth;
import pl.britenet.consoleapp.obj.model.Users;

public class UserMostOrderMonthBuilder {

    private final UserMostOrderMonth userMostOrderMonth;

    public UserMostOrderMonthBuilder(int usersId){
        this.userMostOrderMonth = new UserMostOrderMonth(usersId);
    }


    public UserMostOrderMonthBuilder setMonth(int month){
        this.userMostOrderMonth.setMonth(month);
        return this;
    }

    public UserMostOrderMonthBuilder setNumberOfOrders(int numberOfOrders){
        this.userMostOrderMonth.setNumberOfOrders(numberOfOrders);
        return this;
    }

    public UserMostOrderMonth getUserMostOrderMonth() {
        return this.userMostOrderMonth;
    }


}
