package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.obj.model.NumberOfOrders;
import pl.britenet.consoleapp.obj.model.NumberOfProductSold;

public class NumberOfProductSoldBuilder {

    private final NumberOfProductSold numberOfProductSold;

    public NumberOfProductSoldBuilder(int productId){
        this.numberOfProductSold = new NumberOfProductSold(productId);
    }

    public NumberOfProductSoldBuilder setName(String name) {
        this.numberOfProductSold.setName(name);
        return this;
    }

    public NumberOfProductSoldBuilder setNumberOfSold(int numberOfSold) {
        this.numberOfProductSold.setNumberOfSold(numberOfSold);
        return this;
    }

    public NumberOfProductSold getNumberOfProductSold() {
        return this.numberOfProductSold;
    }
}
