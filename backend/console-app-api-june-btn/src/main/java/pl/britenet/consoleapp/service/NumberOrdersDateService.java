package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.NumberOrdersDateBuilder;
import pl.britenet.consoleapp.obj.model.NumberOrdersDate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class NumberOrdersDateService {
    private final DatabaseService databaseService;

    public NumberOrdersDateService(DatabaseService databaseService){
        this.databaseService = databaseService;
    }

    public Optional<Collection<NumberOrdersDate>> getAllRows(){
        return this.databaseService.performSQL(
                "select Month(orders.date) Miesiac, Year(orders.Date) Rok ,count(orders.Orders_id) Liczba_zamowien\n" +
                        "from orders\n" +
                        "GROUP BY Month(orders.date), Year(orders.Date)",
                resultSet -> {
                    try {
                        Collection<NumberOrdersDate> allRows = new LinkedList<>();

                        while(resultSet.next()) {

                            int month = resultSet.getInt("miesiac");
                            int year = resultSet.getInt("rok");
                            int numberOfOrders = resultSet.getInt("liczba_zamowien");


                            NumberOrdersDate numberOrdersDate = new NumberOrdersDateBuilder()
                                    .setMonth(month)
                                    .setYear(year)
                                    .setNumberOfOrders(numberOfOrders)
                                    .getNumberOrdersDate();

                            allRows.add(numberOrdersDate);
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
