package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.CartBuilder;
import pl.britenet.consoleapp.obj.model.Cart;

import java.sql.SQLException;
import java.util.*;

public class CartService {

    private final DatabaseService databaseService;
    public CartService(DatabaseService databaseService){
        this.databaseService = databaseService;
    }

    public Optional<Collection<Cart>> getAllCart() {

        return this.databaseService.performSQL(
                "SELECT * FROM cart",
                resultSet -> {
                    try {
                        Collection<Cart> allCart = new LinkedList<>();

                        while(resultSet.next()) {

                            int id = resultSet.getInt("cart_id");
                            int usersId = resultSet.getInt("users_id");
                            double totalPrice = resultSet.getDouble("total_price");

                            Cart cart = new CartBuilder(id)
                                    .setUsersId(usersId)
                                    .setTotalPrice(totalPrice)
                                    .getCart();

                            allCart.add(cart);
                        }

                        return allCart;

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }

    public Optional<Cart> getCart(int id) {
        return this.databaseService.performSQL(
                String.format("SELECT * FROM cart WHERE cart_id='%d'", id),
                resultSet -> {
                    try {
                        if (resultSet.next()) {

                            int usersId = resultSet.getInt("users_id");
                            double totalPrice = resultSet.getDouble("total_price");

                            return new CartBuilder(id)
                                    .setUsersId(usersId)
                                    .setTotalPrice(totalPrice)
                                    .getCart();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }


    public Optional<Cart> getCartUser(int userId) {
        return this.databaseService.performSQL(
                String.format("SELECT * FROM cart WHERE users_id='%d'", userId),
                resultSet -> {
                    try {
                        if (resultSet.next()) {

                            int cartId = resultSet.getInt("Cart_id");
                            int usersId = resultSet.getInt("Users_id");
                            double totalPrice = resultSet.getDouble("Total_price");

                            return new CartBuilder(cartId)
                                    .setUsersId(usersId)
                                    .setTotalPrice(totalPrice)
                                    .getCart();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
        );
    }

    public void insertCart(Cart cart) {
        this.databaseService.performDML(
                String.format(Locale.US,"INSERT INTO cart (Users_id,Total_price) " +
                                "VALUES ('%d', '%.2f')",
                        cart.getUsersId(),
                        cart.getTotalPrice())
        );
    }


    public void updateCart(Cart cart) {
        this.databaseService.performDML(
                String.format(Locale.US,"UPDATE cart SET Users_id='%d', Total_price='%.2f' WHERE cart_id='%d'",
                        cart.getUsersId(),
                        cart.getTotalPrice(),
                        cart.getId())
        );
    }

    public void deleteCart(Cart cart) {
        this.databaseService.performDML(
                String.format("DELETE FROM cart WHERE cart_id='%d'",
                        cart.getId())
        );
    }
}
