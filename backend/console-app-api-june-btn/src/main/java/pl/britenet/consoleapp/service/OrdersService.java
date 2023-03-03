package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.OrdersBuilder;
import pl.britenet.consoleapp.obj.model.Orders;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class OrdersService {
    private final DatabaseService databaseService;
    public OrdersService(DatabaseService databaseService){
        this.databaseService = databaseService;
    }
    public Optional<Collection<Orders>> getAllOrders(){
        return this.databaseService.performSQL(
                "SELECT MAX(orders_id) as orders_id FROM orders LIMIT 1;",
                resultSet -> {
                    try {
                        Collection<Orders> allOrders = new LinkedList<>();

                        while(resultSet.next()) {

                            int id = resultSet.getInt("orders_id");

                            Orders orders = new OrdersBuilder(id)
                                    .getOrders();

                            allOrders.add(orders);
                        }
                        return allOrders;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
        );
    }

    public Optional<Collection<Orders>> getAllOrdersUsers(int userId){
        return this.databaseService.performSQL(
                String.format("select * from orders where users_id ='%d'", userId),
                resultSet -> {
                    try {
                        Collection<Orders> allOrders = new LinkedList<>();

                        while(resultSet.next()) {

                            int id = resultSet.getInt("orders_id");
                            LocalDate date = LocalDate.parse(resultSet.getString("Date"));
                            String status = resultSet.getString("status");
                            String address = resultSet.getString("address");
                            double totalPrice = resultSet.getDouble("total_price");

                            Orders orders = new OrdersBuilder(id)
                                    .setUsersId(userId)
                                    .setDate(date)
                                    .setStatus(status)
                                    .setAddress(address)
                                    .setTotalPrice(totalPrice)
                                    .getOrders();

                            allOrders.add(orders);
                        }
                        return allOrders;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
        );
    }

    public Optional<Orders> getOrders(int id){
        return this.databaseService.performSQL(
                String.format("SELECT * FROM orders WHERE orders_id='%d'", id),
                resultSet -> {
                    try {
                        if (resultSet.next()) {

                            int usersId = resultSet.getInt("users_id");
                            LocalDate date = LocalDate.parse(resultSet.getString("Date"));
                            String status = resultSet.getString("status");
                            String address = resultSet.getString("address");
                            double totalPrice = resultSet.getDouble("total_price");


                            return new OrdersBuilder(id)
                                    .setUsersId(usersId)
                                    .setDate(date)
                                    .setStatus(status)
                                    .setAddress(address)
                                    .setTotalPrice(totalPrice)
                                    .getOrders();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }

    public void insertOrders(Orders orders) {
        this.databaseService.performDML(
                String.format(Locale.US,"INSERT INTO orders (Users_id,Date,Status,Address,Total_price) " +
                                "VALUES ('%d', '%s', '%s' ,'%s', '%.2f')",
                        orders.getUsersId(),
                        orders.getDate(),
                        orders.getStatus(),
                        orders.getAddress(),
                        orders.getTotalPrice())
        );
    }

    public void updateOrders(Orders orders) {
        this.databaseService.performDML(
                String.format(Locale.US,"UPDATE orders SET Users_id='%d',Date = '%s' ,Status='%s', Address = '%s', Total_price = '%.2f' WHERE orders_id='%d'",
                        orders.getUsersId(),
                        orders.getDate(),
                        orders.getStatus(),
                        orders.getAddress(),
                        orders.getTotalPrice(),
                        orders.getId())
        );
    }

    public void deleteOrders(Orders orders) {
        this.databaseService.performDML(
                String.format("DELETE FROM orders WHERE orders_id='%d'",
                        orders.getId())
        );
    }
}
