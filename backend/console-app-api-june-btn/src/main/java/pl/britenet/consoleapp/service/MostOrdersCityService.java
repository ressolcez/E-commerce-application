package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.MostOrdersCityBuilder;
import pl.britenet.consoleapp.obj.model.MostOrdersCity;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class MostOrdersCityService {

    private final DatabaseService databaseService;
    public MostOrdersCityService(DatabaseService databaseService){
        this.databaseService = databaseService;
    }

    public Optional<Collection<MostOrdersCity>> getAllRows() {
        return this.databaseService.performSQL(
                "select orders.Address as city,count(orders.Address) as number_of_order\n" +
                        "from orders\n" +
                        "GROUP BY orders.Address\n" +
                        "Order By number_of_order DESC\n" +
                        "LIMIT 1;",

                resultSet -> {
                    try {
                        Collection<MostOrdersCity> allRows = new LinkedList<>();

                        while(resultSet.next()) {
                            int number_of_order = resultSet.getInt("number_of_order");
                            String address = resultSet.getString("city");

                            MostOrdersCity mostOrdersCity = new MostOrdersCityBuilder()
                                    .setAddress(address)
                                    .setNumberOfOrders(number_of_order)
                                    .getMostOrdersCity();
                            allRows.add(mostOrdersCity);
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
