package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.OrdersBuilder;
import pl.britenet.consoleapp.obj.builder.OrdersWithAddressBuilder;
import pl.britenet.consoleapp.obj.model.Orders;
import pl.britenet.consoleapp.obj.model.OrdersWithAddress;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class OrderWithAddressService {

    private final DatabaseService databaseService;
    public OrderWithAddressService(DatabaseService databaseService){
        this.databaseService = databaseService;
    }

    public Optional<Collection<OrdersWithAddress>> getAllRows(){
        return this.databaseService.performSQL(
                "SELECT orders.Orders_id, orders.Address,orders.Date\n" +
                        "FROM users \n" +
                        "RIGHT JOIN orders ON users.Users_Id=orders.Users_id\n" +
                        "where Orders.Status = 'Wysłano'",
                resultSet -> {
                    try {
                        Collection<OrdersWithAddress> allRows = new LinkedList<>();

                        while(resultSet.next()) {

                            int ordersId = resultSet.getInt("orders_id");
                            String address = resultSet.getString("Address");
                            LocalDate date = LocalDate.parse(resultSet.getString("Date"));

                            OrdersWithAddress ordersWithAddress = new OrdersWithAddressBuilder(ordersId)
                                    .setAddress(address)
                                    .setDate(date)
                                    .getOrdersWithAddress();


                            allRows.add(ordersWithAddress);
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
