package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.NumberOfProductSold;
import pl.britenet.consoleapp.obj.model.UserMostOrders;
import pl.britenet.consoleapp.service.PaginationService;
import pl.britenet.consoleapp.service.UserMostOrdersService;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetUsersMostOrdersCommand extends Command{

    private final UserMostOrdersService userMostOrdersService;

    public GetUsersMostOrdersCommand(UserMostOrdersService userMostOrdersService) {
        super(Constants.COMMAND_GET_USERMOSTORDERS);
        this.userMostOrdersService = userMostOrdersService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        Collection<UserMostOrders> allRows = this.userMostOrdersService.getAllRows().get();
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

        PaginationService<UserMostOrders> paginationService = new PaginationService<>(allRows,amount);

        ON = true;

        System.out.println("Użytkownicy, którzy zrealizowali największą ilość zamówień");

        while(ON) {
            System.out.println("Co chcesz zrobić?");
            String command = scanner.nextLine();
            paginationService.PaginationMenu(command).forEach(element ->
                    System.out.println("Id użytkownika: " + element.getUsersId()
                            + " Imie użytkownika: " + element.getName()
                            + " Nazwisko użytkownika: " + element.getSurname()
                            + " Liczba zamówień: " + element.getNumberOfOrders()
                    ));
        }
    }
}
