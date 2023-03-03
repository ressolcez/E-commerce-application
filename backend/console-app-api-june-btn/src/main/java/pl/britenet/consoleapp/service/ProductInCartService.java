package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.CartBuilder;
import pl.britenet.consoleapp.obj.builder.CartProductBuilder;
import pl.britenet.consoleapp.obj.builder.ProductBuilder;
import pl.britenet.consoleapp.obj.builder.ProductInCartBuilder;
import pl.britenet.consoleapp.obj.model.Cart;
import pl.britenet.consoleapp.obj.model.CartProduct;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.obj.model.ProductInCart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class ProductInCartService {
    private final DatabaseService databaseService;
    public ProductInCartService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<Collection<ProductInCart>> getProductInCart(int user_id) {
        return this.databaseService.performSQL(
                String.format("SELECT * FROM cart_product cp\n" +
                        "JOIN product p ON cp.product_id=p.Product_id\n" +
                        "JOIN cart c ON cp.Cart_id=c.Cart_id\n" +
                        "where c.users_id='%d'", user_id),
                resultSet -> {
                    try {
                        Collection<ProductInCart> userCartsCollection = new ArrayList<>();
                        while (resultSet.next()) {

                            int product_id = resultSet.getInt("product_id");
                            int id_cart = resultSet.getInt("cart_id");
                            int cart_product_id = resultSet.getInt("cart_product_id");
                            int quantity = resultSet.getInt("quantity");
                            String name = resultSet.getString("name");
                            double price = resultSet.getDouble("price");
                            String image = resultSet.getString("image");
                            double total_price = resultSet.getDouble("total_Price");

                            CartProduct cartProduct = new CartProductBuilder(cart_product_id)
                                    .setCartId(id_cart)
                                    .setQuantity(quantity)
                                    .getCartProduct();

                            Product product = new ProductBuilder(product_id)
                                    .setName(name)
                                    .setPrice(price)
                                    .setImage(image)
                                    .getProduct();

                            Cart cart = new CartBuilder(id_cart)
                                    .setUsersId(user_id)
                                    .setTotalPrice(total_price)
                                    .getCart();

                            ProductInCart productInCart = new ProductInCartBuilder()
                                    .setCartProduct(cartProduct)
                                    .setCart(cart)
                                    .setProduct(product)
                                    .getProductInCart();

                            userCartsCollection.add(productInCart);
                        }
                        return userCartsCollection;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
    }

}
