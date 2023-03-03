package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Orders;
import pl.britenet.consoleapp.service.OrdersService;

import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

public class GetOrdersCommand extends Command{

    private final OrdersService ordersService;

    public GetOrdersCommand(OrdersService ordersService) {
        super(Constants.COMMAND_GET_ORDERS);
        this.ordersService = ordersService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("Wprowadź ID zamówienia:");
        int id;

        while(true){
            try{
                id = Integer.parseInt(scanner.next());
                break;
            }catch (NumberFormatException e){
                System.out.println("Podaj poprawna liczbe");
            }
        }

        Optional<Orders> orders = this.ordersService.getOrders(id);

        if(orders.isPresent()){
            System.out.println("Id produktu: " + orders.get().getId());
            System.out.println("Id użytkownika: " + orders.get().getUsersId());
            System.out.println("Data zamówienia:  " + orders.get().getDate());
            System.out.println("Status zamówienia: " + orders.get().getStatus());
            System.out.println("Adres zamówienia: " + orders.get().getAddress());
            System.out.println("Cena zamówienia: " + orders.get().getTotalPrice());

        }else{
            System.out.println("Nie ma zamówienia o takim ID");
        }

    }
}
