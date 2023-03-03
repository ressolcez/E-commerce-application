package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.CartProductBuilder;
import pl.britenet.consoleapp.obj.model.CartProduct;

import java.sql.SQLException;
import java.util.*;

public class CartProductService {

    private final DatabaseService databaseService;
    public CartProductService(DatabaseService databaseService){

        this.databaseService = databaseService;
    }

    public Optional<Collection<CartProduct>> getAllCartProduct() {
        return this.databaseService.performSQL(
                "SELECT * FROM cart_product",
                resultSet -> {
                    try {
                        Collection<CartProduct> allCartProduct = new LinkedList<>();

                        while(resultSet.next()) {

                            int id = resultSet.getInt("cart_product_id");
                            int cartId = resultSet.getInt("cart_id");
                            int productId = resultSet.getInt("product_id");
                            int quantity = resultSet.getInt("quantity");

                            CartProduct cartProduct = new CartProductBuilder(id)
                                    .setCartId(cartId)
                                    .setProductId(productId)
                                    .setQuantity(quantity)
                                    .getCartProduct();

                            allCartProduct.add(cartProduct);
                        }

                        return allCartProduct;

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }

    public Optional<CartProduct> getCartProduct(int id){
        return this.databaseService.performSQL(
                String.format("SELECT * FROM cart_product WHERE cart_product_id='%d'", id),
                resultSet -> {
                    try {
                        if (resultSet.next()) {

                            int cartId = resultSet.getInt("cart_id");
                            int productId = resultSet.getInt("product_id");
                            int quantity = resultSet.getInt("quantity");

                            return new CartProductBuilder(id)
                                    .setCartId(cartId)
                                    .setProductId(productId)
                                    .setQuantity(quantity)
                                    .getCartProduct();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }


    public void insertCartProduct(CartProduct cartProduct, String sign) {
        this.databaseService.performDML(
                String.format("INSERT INTO Cart_product (Cart_id,Product_id,Quantity) " +
                                "VALUES ('%d', '%d', '%d') ON DUPLICATE KEY UPDATE quantity = quantity"+ sign +" 1;",
                        cartProduct.getCartId(),
                        cartProduct.getProductId(),
                        cartProduct.getQuantity())
        );
    }

    public void updateCartProduct(CartProduct cartProduct) {
        this.databaseService.performDML(
                String.format("UPDATE cart_product SET Cart_id='%d', Product_id='%d', Quantity='%d' WHERE cart_product_id='%d'",
                        cartProduct.getCartId(),
                        cartProduct.getProductId(),
                        cartProduct.getQuantity(),
                        cartProduct.getId())
        );
    }

    public void deleteCartProduct(CartProduct cartProduct) {
        this.databaseService.performDML(
                String.format("DELETE FROM cart_product WHERE cart_product_id='%d'",
                        cartProduct.getId())
        );
    }
}

