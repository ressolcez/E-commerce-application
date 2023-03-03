package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.NumberProductCartBuilder;
import pl.britenet.consoleapp.obj.model.NumberProductCart;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class NumberProductCartService {
    private final DatabaseService databaseService;
    public NumberProductCartService(DatabaseService databaseService){
        this.databaseService = databaseService;
    }

    public Optional<Collection<NumberProductCart>> getAllRows(){
        return this.databaseService.performSQL(
                "select users.Users_id,users.Name,users.Surname,COUNT(cart_product.Product_id) liczba_produktow\n" +
                        "from cart\n" +
                        "left join cart_product  ON Cart.Cart_id = cart_product.Cart_id\n" +
                        "left join users ON users.Users_Id = cart.Users_id\n" +
                        "GROUP By cart.Users_Id",
                resultSet -> {
                    try {
                        Collection<NumberProductCart> allRows = new LinkedList<>();

                        while(resultSet.next()) {

                            int usersId = resultSet.getInt("users_id");
                            String name = resultSet.getString("Name");
                            String surname = resultSet.getString("surname");
                            int numberOfProducts = resultSet.getInt("liczba_produktow");

                            NumberProductCart numberProductCart = new NumberProductCartBuilder(usersId)
                                    .setName(name)
                                    .setSurname(surname)
                                    .setNumberOfProducts(numberOfProducts)
                                    .getNumberProductCart();


                            allRows.add(numberProductCart);
                        }
                        return allRows;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }


}
