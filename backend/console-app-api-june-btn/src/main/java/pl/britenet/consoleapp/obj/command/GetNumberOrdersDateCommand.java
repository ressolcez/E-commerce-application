package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.NumberOrdersDate;
import pl.britenet.consoleapp.obj.model.Users;
import pl.britenet.consoleapp.service.NumberOrdersDateService;
import pl.britenet.consoleapp.service.PaginationService;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetNumberOrdersDateCommand extends Command{

    private final NumberOrdersDateService numberOrdersDateService;

    public GetNumberOrdersDateCommand(NumberOrdersDateService numberOrdersDateService) {
        super(Constants.COMMAND_GET_NUMBERORDERSDATE);
        this.numberOrdersDateService = numberOrdersDateService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        Collection<NumberOrdersDate> allRows = this.numberOrdersDateService.getAllRows().get();
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

        PaginationService<NumberOrdersDate> paginationService = new PaginationService<>(allRows,amount);

        ON = true;

        while(ON) {
            System.out.println("Co chcesz zrobić?");
            String command = scanner.nextLine();
            paginationService.PaginationMenu(command).forEach(element ->
                    System.out.println("Miesiac: " + element.getMonth()
                            + " Rok: " + element.getYear()
                            + " Liczba zamówień: " + element.getNumberOfOrders()
                    ));
        }
    }
}
