package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.ProductBetweenPriceBuilder;
import pl.britenet.consoleapp.obj.model.ProductBetweenPrice;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class ProductBetweenPriceService {

    private final DatabaseService databaseService;

    public ProductBetweenPriceService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<Collection<ProductBetweenPrice>> getAllRows() {
        return this.databaseService.performSQL(
                "select product.Product_id,product.name,product.Price\n" +
                        "from product\n" +
                        "where product.Price BETWEEN 100 AND 200",
                resultSet -> {
                    try {
                        Collection<ProductBetweenPrice> allRows = new LinkedList<>();

                        while(resultSet.next()) {

                            String name = resultSet.getString("name");
                            double price = resultSet.getDouble("price");
                            int id = resultSet.getInt("product_id");

                            ProductBetweenPrice productBetweenPrice = new ProductBetweenPriceBuilder(id)
                                    .setName(name)
                                    .setPrice(price)
                                    .getProductBetweenPrice();

                            allRows.add(productBetweenPrice);
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
