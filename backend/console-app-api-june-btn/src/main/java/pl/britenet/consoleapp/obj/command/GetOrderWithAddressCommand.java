package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.NumberOfProductSold;
import pl.britenet.consoleapp.obj.model.OrdersWithAddress;
import pl.britenet.consoleapp.service.OrderWithAddressService;
import pl.britenet.consoleapp.service.PaginationService;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetOrderWithAddressCommand extends Command{
    private OrderWithAddressService orderWithAddressService;

    public GetOrderWithAddressCommand(OrderWithAddressService orderWithAddressService) {
        super(Constants.COMMAND_GET_ORDERSWITHADDRESS);
        this.orderWithAddressService = orderWithAddressService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        Collection<OrdersWithAddress> allRows = this.orderWithAddressService.getAllRows().get();
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

        PaginationService<OrdersWithAddress> paginationService = new PaginationService<>(allRows,amount);

        ON = true;

        System.out.println("Adresy na które zostały wysłane zamówienia oraz data zamówienia");


        while(ON) {
            System.out.println("Co chcesz zrobić?");
            String command = scanner.nextLine();
            paginationService.PaginationMenu(command).forEach(element ->
                    System.out.println("Id zamówienia: " + element.getOrdersId()
                            + " Adres zamówienia: " + element.getAddress()
                            + " Data zamówienia: " + element.getDate()
                    ));
        }
    }
}
