package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.UserMostOrderMonthBuilder;
import pl.britenet.consoleapp.obj.model.UserMostOrderMonth;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class UserMostOrderMonthService {
    private final DatabaseService databaseService;
    public UserMostOrderMonthService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<Collection<UserMostOrderMonth>> getAllRows() {
        return this.databaseService.performSQL(
                "SELECT subquery_orders.userid, MONTH(orders.date) Miesiac, subquery_orders.liczba_zakupow\n" +
                        "FROM orders \n" +
                        "JOIN (\n" +
                        "SELECT users.Users_Id userid, orders.Orders_id, MONTH(orders.date), COUNT(orders.Orders_id) as liczba_zakupow\n" +
                        "FROM orders\n" +
                        "JOIN users  ON orders.Users_id=users.Users_Id\n" +
                        "GROUP BY MONTH(orders.date), users.Users_Id\n" +
                        "ORDER BY COUNT(orders.Orders_id) DESC\n" +
                        ") subquery_orders ON subquery_orders.userid=orders.Users_id\n" +
                        "GROUP BY  MONTH(orders.date)\n"
                        ,
                resultSet -> {
                    try {
                        Collection<UserMostOrderMonth> allRows = new LinkedList<>();

                        while(resultSet.next()) {

                            int usersId = resultSet.getInt("userid");
                            int month = resultSet.getInt("Miesiac");
                            int numerOfOrders = resultSet.getInt("liczba_zakupow");

                            UserMostOrderMonth userMostOrderMonth = new UserMostOrderMonthBuilder(usersId)
                                    .setMonth(month)
                                    .setNumberOfOrders(numerOfOrders)
                                    .getUserMostOrderMonth();
                            allRows.add(userMostOrderMonth);
                        }
                        return allRows;

                    } catch (SQLException e) {
                        e.getMessage();
                    }
                    return null;
                }
        );
    }
}
