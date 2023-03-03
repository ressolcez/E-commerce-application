package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.obj.model.Cart;
import pl.britenet.consoleapp.obj.model.CartProduct;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.obj.model.ProductInCart;

public class ProductInCartBuilder {

    private final ProductInCart productInCart;

    public ProductInCartBuilder() {
        this.productInCart = new ProductInCart();
    }


    public ProductInCartBuilder setProduct(Product product) {
        this.productInCart.setProduct(product);
        return this;
    }
    public ProductInCartBuilder setCart(Cart cart) {
        this.productInCart.setCart(cart);
        return this;
    }
    public ProductInCartBuilder setCartProduct(CartProduct cartProduct) {
        this.productInCart.setCartProduct(cartProduct);
        return this;
    }

    public ProductInCart getProductInCart() {
        return this.productInCart;
    }


}
