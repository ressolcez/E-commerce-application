package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.obj.model.CartProduct;
import pl.britenet.consoleapp.obj.model.NumberOfOrders;

public class NumberOfOrdersBuilder {
    private final NumberOfOrders numberOfOrders;

    public NumberOfOrdersBuilder(int userId){
        this.numberOfOrders = new NumberOfOrders(userId);
    }

    public NumberOfOrdersBuilder setName(String name) {
        this.numberOfOrders.setName(name);
        return this;
    }

    public NumberOfOrdersBuilder setSurrname(String surrname) {
        this.numberOfOrders.setSurrname(surrname);
        return this;
    }

    public NumberOfOrdersBuilder setPhone(String phone) {
        this.numberOfOrders.setPhone(phone);
        return this;
    }

    public NumberOfOrdersBuilder setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders.setNumberOfOrders(numberOfOrders);
        return this;
    }

    public NumberOfOrders getNumberOfOrders() {
        return this.numberOfOrders;
    }
}
