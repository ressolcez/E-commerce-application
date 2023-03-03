package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.OrderProductBuilder;
import pl.britenet.consoleapp.obj.model.OrderProduct;

import java.sql.SQLException;
import java.util.*;

public class OrderProductService {
    private final DatabaseService databaseService;
    public OrderProductService(DatabaseService databaseService){
        this.databaseService = databaseService;
    }
    public Optional<Collection<OrderProduct>> getAllOrderProduct(){
        return this.databaseService.performSQL(
                "SELECT * FROM order_product",
                resultSet -> {
                    try {
                        Collection<OrderProduct> allOrderProduct = new LinkedList<>();

                        while(resultSet.next()) {

                            int id = resultSet.getInt("order_product_id");
                            int ordersId = resultSet.getInt("orders_id");
                            int productId = resultSet.getInt("product_id");
                            int quantity = resultSet.getInt("quantity");
                            double price = resultSet.getDouble("price");

                            OrderProduct orderProduct = new OrderProductBuilder(id)
                                    .setOrdersId(ordersId)
                                    .setProductId(productId)
                                    .setQuantity(quantity)
                                    .setPrice(price)
                                    .getOrderProduct();

                            allOrderProduct.add(orderProduct);
                        }

                        return allOrderProduct;

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }

    public Optional<OrderProduct> getOrderProduct(int id){
        return this.databaseService.performSQL(
                String.format("SELECT * FROM order_product WHERE order_product_id='%d'", id),
                resultSet -> {
                    try {
                        if (resultSet.next()) {

                            int ordersId = resultSet.getInt("orders_id");
                            int productId = resultSet.getInt("product_id");
                            int quantity = resultSet.getInt("quantity");
                            double price = resultSet.getDouble("price");

                            return new OrderProductBuilder(id)
                                    .setOrdersId(ordersId)
                                    .setProductId(productId)
                                    .setQuantity(quantity)
                                    .setPrice(price)
                                    .getOrderProduct();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }

    public void insertOrderProduct(OrderProduct orderProduct) {
        this.databaseService.performDML(
                String.format(Locale.US,"INSERT INTO order_product (Orders_id,Product_id,Quantity,Price) " +
                                "VALUES ('%d', '%d', '%d', '%.2f')",
                        orderProduct.getOrdersId(),
                        orderProduct.getProductId(),
                        orderProduct.getQuantity(),
                        orderProduct.getPrice())
        );
    }

    public void updateOrderProduct(OrderProduct orderProduct) {
        this.databaseService.performDML(
                String.format(Locale.US,"UPDATE order_product SET Orders_id='%d',Product_id = '%s' ,Quantity='%s', Price = '%.2f' WHERE order_product_id='%d'",
                        orderProduct.getOrdersId(),
                        orderProduct.getProductId(),
                        orderProduct.getQuantity(),
                        orderProduct.getPrice(),
                        orderProduct.getId())
        );
    }

    public void deleteOrderProduct(OrderProduct orderProduct) {
        this.databaseService.performDML(
                String.format("DELETE FROM order_product WHERE order_product_id='%d'",
                        orderProduct.getId())
        );
    }
}
