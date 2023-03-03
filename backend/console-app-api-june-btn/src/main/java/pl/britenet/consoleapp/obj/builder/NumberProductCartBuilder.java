package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.obj.model.NumberOfOrders;
import pl.britenet.consoleapp.obj.model.NumberProductCart;

public class NumberProductCartBuilder {

    private final NumberProductCart numberProductCart;

    public NumberProductCartBuilder(int userId){
        this.numberProductCart = new NumberProductCart(userId);
    }

    public NumberProductCartBuilder setName(String name) {
        this.numberProductCart.setName(name);
        return this;
    }

    public NumberProductCartBuilder setSurname(String surname){
        this.numberProductCart.setSurname(surname);
        return this;
    }

    public NumberProductCartBuilder setNumberOfProducts(int numberOfProducts){
        this.numberProductCart.setNumberOfProducts(numberOfProducts);
        return this;
    }

    public NumberProductCart getNumberProductCart() {
        return this.numberProductCart;
    }


}
