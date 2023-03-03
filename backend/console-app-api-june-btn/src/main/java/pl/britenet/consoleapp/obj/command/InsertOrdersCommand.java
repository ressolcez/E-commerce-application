package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.OrdersBuilder;
import pl.britenet.consoleapp.obj.model.Orders;
import pl.britenet.consoleapp.service.OrdersService;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class InsertOrdersCommand extends Command{

    private final OrdersService ordersService;

    public InsertOrdersCommand(OrdersService ordersService) {
        super(Constants.COMMAND_INSERT_ORDERS);
        this.ordersService = ordersService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        try{
        System.out.println("Wprowadź ID użytkownika");
        int userId = scanner.nextInt();

        System.out.println("Wprowadź Date zamówienia");

        LocalDate date = LocalDate.parse(scanner.next());

        scanner.nextLine();

        System.out.println("Wprowadź Status zamówienia");
        String status = scanner.nextLine();

        System.out.println("Wprowadź adres zamówienia");
        String address = scanner.nextLine();

        System.out.println("Wprowadź cene zamówienia");
        double totalPrice = scanner.nextDouble();

        Orders orders = new OrdersBuilder()
                .setUsersId(userId)
                .setDate(date)
                .setStatus(status)
                .setAddress(address)
                .setTotalPrice(totalPrice)
                .getOrders();

        this.ordersService.insertOrders(orders);
        System.out.println("Zamówienie zostało utworzone!");
        }catch (IllegalStateException | InputMismatchException e){
            if(e.getMessage() == null){
                System.out.println("Wpisano niepoprawne dane");
            }else {
                System.out.println("Wpisano niepoprawne dane: " + e.getMessage());
            }
        }
    }
}
