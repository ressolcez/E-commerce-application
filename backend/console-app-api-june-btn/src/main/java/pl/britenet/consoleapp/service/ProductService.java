package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.ProductBuilder;
import pl.britenet.consoleapp.obj.model.Product;

import java.sql.SQLException;
import java.util.*;

public class ProductService {

    private final DatabaseService databaseService;
    public ProductService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<Collection<Product>> getAllProducts() {
        return this.databaseService.performSQL(
                "SELECT * FROM product",
                resultSet -> {
                    try {
                        Collection<Product> Allproducts = new LinkedList<>();

                        while(resultSet.next()) {

                            String name = resultSet.getString("name");
                            double price = resultSet.getDouble("price");
                            String description = resultSet.getString("description");
                            int id = resultSet.getInt("product_id");
                            String image = resultSet.getString("image");

                            Product product = new ProductBuilder(id)
                                    .setName(name)
                                    .setDescription(description)
                                    .setPrice(price)
                                    .setImage(image)
                                    .getProduct();

                             Allproducts.add(product);
                        }

                        return Allproducts;

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }

    public Optional<Product> getProduct(int id) {
        return this.databaseService.performSQL(
                String.format("SELECT * FROM product WHERE product_id='%d'", id),
                resultSet -> {
                    try {
                        if (resultSet.next()) {

                            String name = resultSet.getString("name");
                            double price = resultSet.getDouble("price");
                            String description = resultSet.getString("description");
                            String image = resultSet.getString("image");

                            return new ProductBuilder(id)
                                    .setName(name)
                                    .setDescription(description)
                                    .setPrice(price)
                                    .setImage(image)
                                    .getProduct();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }
    public void insertProduct(Product product) {

        this.databaseService.performDML(

                String.format(Locale.US,"INSERT INTO product (name,description,price,image) " +
                                "VALUES ('%s', '%s','%.2f','%s')",
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getImage())
        );
    }

    public void updateProduct(Product product) {

        this.databaseService.performDML(
                String.format("UPDATE product SET name='%s',description = '%s' ,price='%.2f', image = '%s' WHERE Product_id='%d'",
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getImage(),
                        product.getId())
        );
    }

    public void deleteProduct(Product product) {

            this.databaseService.performDML(
                    String.format("DELETE FROM product WHERE Product_id='%d'",
                            product.getId())
            );
    }
}
