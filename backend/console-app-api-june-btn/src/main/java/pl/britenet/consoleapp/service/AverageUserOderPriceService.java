package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.AverageUserOderPriceBuilder;
import pl.britenet.consoleapp.obj.model.AverageUserOrderPrice;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class AverageUserOderPriceService {

    private final DatabaseService databaseService;

    public AverageUserOderPriceService(DatabaseService databaseService){
        this.databaseService = databaseService;
    }

    public Optional<Collection<AverageUserOrderPrice>> getAllRows() {
        return this.databaseService.performSQL(
                "SELECT USERS.Users_Id,users.Name,users.Surname,users.Phone ,AVG(orders.Total_price) Srednia_Cena_zamowien\n" +
                        "FROM USERS\n" +
                        "INNER JOIN orders ON ORDERS.Users_id = users.Users_Id\n" +
                        "GROUP BY users.Users_Id\n" +
                        "ORDER BY Srednia_Cena_zamowien DESC",
                resultSet -> {
                    try {
                        Collection<AverageUserOrderPrice> allRows = new LinkedList<>();

                        while(resultSet.next()) {

                            int usersId = resultSet.getInt("Users_id");
                            String name = resultSet.getString("Name");
                            String surname = resultSet.getString("Surname");
                            String phone = resultSet.getString("Phone");
                            double avgUserOrderPrice = resultSet.getDouble("Srednia_Cena_Zamowien");

                            AverageUserOrderPrice averageUserOrderPrice = new AverageUserOderPriceBuilder(usersId)
                                    .setName(name)
                                    .setSurname(surname)
                                    .setPhone(phone)
                                    .setAvgOrderPrice(avgUserOrderPrice)
                                    .getAverageUserOrderPrice();

                            allRows.add(averageUserOrderPrice);
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
