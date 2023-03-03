package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.NumberOfOrdersBuilder;
import pl.britenet.consoleapp.obj.model.NumberOfOrders;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class NumberOfOrdersService {
    private final DatabaseService databaseService;
    public NumberOfOrdersService(DatabaseService databaseService){
        this.databaseService = databaseService;
    }

    public Optional<Collection<NumberOfOrders>> getAllRows() {
        return this.databaseService.performSQL(
                        "SELECT USERS.Users_Id,users.Name,users.Surname,users.Phone ,COUNT(orders.Orders_id) Liczba_Zamówień\n" +
                                "FROM USERS\n" +
                                "INNER JOIN orders ON ORDERS.Users_id = users.Users_Id\n" +
                                "GROUP BY users.Users_Id\n" +
                                "ORDER BY Liczba_Zamówień DESC",
                resultSet -> {
                    try {
                        Collection<NumberOfOrders> allRows = new LinkedList<>();

                        while(resultSet.next()) {
                            int usersId = resultSet.getInt("Users_Id");
                            String name = resultSet.getString("name");
                            String surrname = resultSet.getString("surname");
                            String phone = resultSet.getString("phone");
                            int numberOfOrdersRows = resultSet.getInt("Liczba_Zamówień");

                            NumberOfOrders numberOfOrders = new NumberOfOrdersBuilder(usersId)
                                    .setName(name)
                                    .setSurrname(surrname)
                                    .setPhone(phone)
                                    .setNumberOfOrders(numberOfOrdersRows)
                                    .getNumberOfOrders();
                            allRows.add(numberOfOrders);
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
