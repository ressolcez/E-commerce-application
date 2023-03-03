package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.NumberOfOrders;
import pl.britenet.consoleapp.obj.model.Users;
import pl.britenet.consoleapp.service.NumberOfOrdersService;
import pl.britenet.consoleapp.service.PaginationService;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetNumberOfOrdersCommand extends Command{

    private final NumberOfOrdersService numberOfOrdersService;

    public GetNumberOfOrdersCommand(NumberOfOrdersService numberOfOrdersService) {
        super(Constants.COMMAND_GET_NUMBEROFORDERS);
        this.numberOfOrdersService = numberOfOrdersService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        Collection<NumberOfOrders> allRows = this.numberOfOrdersService.getAllRows().get();
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

        PaginationService<NumberOfOrders> paginationService = new PaginationService<>(allRows,amount);

        ON = true;

        while(ON) {
            System.out.println("Co chcesz zrobić? (Help wyświetla wszystkie komendy)");
            String command = scanner.nextLine();
            paginationService.PaginationMenu(command).forEach(element ->
                    System.out.println("Id Użytkownika: " + element.getUsersId()
                            + " Imie użytkownika:  " + element.getName()
                            + " Nazwisko użytkownika: " + element.getSurrname()
                            + " Telefon: " + element.getPhone()
                            + " Liczba zamówień: " + element.getNumberOfOrders()
                    ));
        }
    }
}
