package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.NumberOfProductSoldBuilder;
import pl.britenet.consoleapp.obj.model.NumberOfProductSold;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class NumberOfProductSoldService {
    private final DatabaseService databaseService;

    public NumberOfProductSoldService(DatabaseService databaseService){
        this.databaseService = databaseService;
    }

    public Optional<Collection<NumberOfProductSold>> getAllRows(){
        return this.databaseService.performSQL(
                "select product.Product_id,product.name as nazwa,COUNT(Order_Product.Orders_id) as Liczba_sprzedanych_sztuk\n" +
                        "from product\n" +
                        "Left join order_product ON order_product.Product_id = product.Product_id\n" +
                        "WHERE product.name = 'Monitor'\n" +
                        "GROUP BY product.name",
                resultSet -> {
                    try {
                        Collection<NumberOfProductSold> allRows = new LinkedList<>();

                        while(resultSet.next()) {

                            int productId = resultSet.getInt("Product_id");
                            String name = resultSet.getString("nazwa");
                            int numberOfSold =  resultSet.getInt("Liczba_sprzedanych_sztuk");

                            NumberOfProductSold numberOfProductSold = new NumberOfProductSoldBuilder(productId)
                                    .setName(name)
                                    .setNumberOfSold(numberOfSold)
                                    .getNumberOfProductSold();

                            allRows.add(numberOfProductSold);
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
