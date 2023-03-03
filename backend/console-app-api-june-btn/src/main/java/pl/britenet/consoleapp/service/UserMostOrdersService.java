package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.UsersBuilder;
import pl.britenet.consoleapp.obj.builder.UsersMostOrdersBuilder;
import pl.britenet.consoleapp.obj.model.UserMostOrders;
import pl.britenet.consoleapp.obj.model.Users;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class UserMostOrdersService {

    private final DatabaseService databaseService;
    public UserMostOrdersService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<Collection<UserMostOrders>> getAllRows() {
        return this.databaseService.performSQL(
                "select users.Users_Id,users.Name,users.Surname, subquery2_users.Liczba_zamówień\n" +
                        "from users\n" +
                        "Join(\n" +
                        "SELECT USERS.Users_Id userId,users.Name,users.Surname,users.Phone ,COUNT(orders.Orders_id) Liczba_Zamówień\n" +
                        "FROM USERS\n" +
                        "INNER JOIN orders ON ORDERS.Users_id = users.Users_Id\n" +
                        "GROUP BY users.Users_Id\n" +
                        "ORDER BY Liczba_Zamówień DESC\n" +
                        ")subquery2_users on subquery2_users.userId = users.Users_Id\n" +
                        "where subquery2_users.Liczba_zamówień = (\n" +
                        "select MAX(l_zamowien)\n" +
                        "from users\n" +
                        "join(\n" +
                        "SELECT USERS.Users_Id userId,COUNT(orders.Orders_id) l_zamowien\n" +
                        "FROM USERS\n" +
                        "inner JOIN orders ON ORDERS.Users_id = users.Users_Id\n" +
                        "GROUP BY users.Users_Id \n" +
                        ") subquery_users on subquery_users.userId = users.Users_Id\n" +
                        ");",
                resultSet -> {
                    try {
                        Collection<UserMostOrders> allRows = new LinkedList<>();

                        while(resultSet.next()) {

                            int usersId = resultSet.getInt("users_id");
                            String name = resultSet.getString("name");
                            String surname = resultSet.getString("surname");
                            int numberOfOrders = resultSet.getInt("Liczba_zamówień");


                            UserMostOrders userMostOrders = new UsersMostOrdersBuilder(usersId)
                                    .setName(name)
                                    .setSurname(surname)
                                    .setNumberOfOrders(numberOfOrders)
                                    .getUserMostOrders();

                            allRows.add(userMostOrders);
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
