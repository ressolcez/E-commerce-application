package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Orders;
import pl.britenet.consoleapp.obj.model.Users;
import pl.britenet.consoleapp.service.OrdersService;
import pl.britenet.consoleapp.service.PaginationService;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;


public class GetAllOrdersCommand extends Command{

    private final OrdersService ordersService;

    public GetAllOrdersCommand(OrdersService ordersService) {
        super(Constants.COMMAND_GET_ALLORDERS);
        this.ordersService = ordersService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        Collection<Orders> allRows = this.ordersService.getAllOrders().get();
        System.out.println("Podaj ilość wierszy");

        int amount;

        while(true){
            try{
                amount = Integer.parseInt(scanner.next());
                break;
            }catch (NumberFormatException e){
                System.out.println("Podaj poprawna liczbe");
            }
        }

        if(amount < 1){
            System.out.println("Liczba rekordów musi być większa od 0");
            return;
        }

        scanner.nextLine();

        PaginationService<Orders> paginationService = new PaginationService<>(allRows,amount);

        ON = true;

        while(ON) {
            System.out.println("Co chcesz zrobić? (Help wyświetla wszystkie komendy)");
            String command = scanner.nextLine();
            paginationService.PaginationMenu(command).forEach(order ->
                    System.out.println("Id zamówienia " + order.getId()
                            + " Id Użytkownika: " + order.getUsersId()
                            + " Data zamówienia: " + order.getDate()
                            + " Status zamówienia: " + order.getStatus()
                            + " Adres zamówienia: " + order.getAddress()
                            + " Cena zamówienia: " + order.getTotalPrice()
            ));
        }


    }
}

