package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Cart;

public class CartBuilder {
    private final Cart cart;

    public CartBuilder(int id){
        this.cart = new Cart(id);
    }

    public CartBuilder(){
        this.cart = new Cart();
    }

    public CartBuilder setUsersId(int userId) {
        this.cart.setUsersId(userId);
        return this;
    }

    public CartBuilder setTotalPrice(double price){
        this.cart.setTotalPrice(price);
        return this;
    }

    public Cart getCart(){
        return this.cart;
    }
}
