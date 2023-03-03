package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.obj.model.MostOrdersCity;

public class MostOrdersCityBuilder {
    private final MostOrdersCity mostOrdersCity;

    public MostOrdersCityBuilder(){
        this.mostOrdersCity = new MostOrdersCity();
    }

    public MostOrdersCityBuilder setAddress(String address) {
        this.mostOrdersCity.setAddress(address);
        return this;
    }

    public MostOrdersCityBuilder setNumberOfOrders(int numberOfOrders) {
        this.mostOrdersCity.setNumber_of_orders(numberOfOrders);
        return this;
    }

    public MostOrdersCity getMostOrdersCity(){
        return this.mostOrdersCity;
    }
}
