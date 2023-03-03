package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.obj.model.NumberOrdersDate;

public class NumberOrdersDateBuilder {
    private final NumberOrdersDate numberOrdersDate;

    public NumberOrdersDateBuilder(){
        this.numberOrdersDate = new NumberOrdersDate();
    }

    public NumberOrdersDateBuilder setMonth(int month) {
        this.numberOrdersDate.setMonth(month);
        return this;
    }

    public NumberOrdersDateBuilder setYear(int year) {
        this.numberOrdersDate.setYear(year);
        return this;
    }

    public NumberOrdersDateBuilder setNumberOfOrders(int numberOfOrders) {
        this.numberOrdersDate.setNumberOfOrders(numberOfOrders);
        return this;
    }

    public NumberOrdersDate getNumberOrdersDate() {
        return numberOrdersDate;
    }
}
