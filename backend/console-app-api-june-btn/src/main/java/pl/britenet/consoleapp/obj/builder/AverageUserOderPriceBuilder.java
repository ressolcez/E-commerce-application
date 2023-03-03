package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.obj.model.AverageUserOrderPrice;
import pl.britenet.consoleapp.obj.model.NumberOfOrders;

public class AverageUserOderPriceBuilder {
    private final AverageUserOrderPrice averageUserOrderPrice;
    public AverageUserOderPriceBuilder(int userId){
        this.averageUserOrderPrice = new AverageUserOrderPrice(userId);
    }

    public AverageUserOderPriceBuilder setName(String name) {
        this.averageUserOrderPrice.setName(name);
        return this;
    }

    public AverageUserOderPriceBuilder setSurname(String surname) {
        this.averageUserOrderPrice.setSurname(surname);
        return this;
    }

    public AverageUserOderPriceBuilder setPhone(String phone) {
        this.averageUserOrderPrice.setPhone(phone);
        return this;
    }

    public AverageUserOderPriceBuilder setAvgOrderPrice(double avgOrderPrice) {
        this.averageUserOrderPrice.setAvgPriceOrder(avgOrderPrice);
        return this;
    }

    public AverageUserOrderPrice getAverageUserOrderPrice() {
        return this.averageUserOrderPrice;
    }


}
