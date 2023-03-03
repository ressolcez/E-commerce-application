package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.OrdersBuilder;
import pl.britenet.consoleapp.obj.model.Orders;
import pl.britenet.consoleapp.service.OrdersService;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UpdateOrdersCommand extends Command{
    private final OrdersService ordersService;

    public UpdateOrdersCommand(OrdersService ordersService) {
        super(Constants.COMMAND_UPDATE_ORDERS);
        this.ordersService = ordersService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        try{
        System.out.println("Wprowadź ID zamówienia do zmodyfikowania:");
            int id;

            while(true){
                try{
                    id = Integer.parseInt(scanner.next());
                    break;
                }catch (NumberFormatException e){
                    System.out.println("Podaj poprawna liczbe");
                }
            }

        if(this.ordersService.getOrders(id).isEmpty()){
            System.out.println("Nie ma takiego Id");
            return;
        }

        System.out.println("Wprowadź ID użytkownika:");
        int userId = scanner.nextInt();

        System.out.println("Wprowadź Date zamówienia:");
        scanner.nextLine();

        String dateString = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateString);

        System.out.println("Wprowadź Status zamówienia:");
        String status = scanner.nextLine();

        System.out.println("Wprowadź adres zamówienia:");
        String address = scanner.nextLine();

        System.out.println("Wprowadź cene zamówienia:");
        double totalPrice = scanner.nextDouble();

        Orders orders = new OrdersBuilder(id)
                .setUsersId(userId)
                .setDate(date)
                .setStatus(status)
                .setAddress(address)
                .setTotalPrice(totalPrice)
                .getOrders();

        this.ordersService.updateOrders(orders);
        System.out.println("Zamówienie zostało zmodyfikowane!");

        }catch (IllegalStateException | InputMismatchException e){
            if(e.getMessage() == null){
                System.out.println("Wpisano niepoprawne dane");
            }else {
                System.out.println("Wpisano niepoprawne dane: " + e.getMessage());
            }
        }

    }
}
