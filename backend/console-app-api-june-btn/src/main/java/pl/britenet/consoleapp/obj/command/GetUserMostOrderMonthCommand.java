package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.NumberOfProductSold;
import pl.britenet.consoleapp.obj.model.UserMostOrderMonth;
import pl.britenet.consoleapp.service.PaginationService;
import pl.britenet.consoleapp.service.UserMostOrderMonthService;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetUserMostOrderMonthCommand extends Command{

    private final UserMostOrderMonthService userMostOrderMonthService;

    public GetUserMostOrderMonthCommand(UserMostOrderMonthService userMostOrderMonthService) {
        super(Constants.COMMAND_GET_USERMOSTORDERMONTH);
        this.userMostOrderMonthService = userMostOrderMonthService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        Collection<UserMostOrderMonth> allRows = this.userMostOrderMonthService.getAllRows().get();
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

        PaginationService<UserMostOrderMonth> paginationService = new PaginationService<>(allRows,amount);

        ON = true;

        System.out.println("Użytkownik, ktory wykonal najwiecej zakupow w każdym miesiacu");

        while(ON) {
            System.out.println("Co chcesz zrobić?");
            String command = scanner.nextLine();
            paginationService.PaginationMenu(command).forEach(element ->
                    System.out.println("Id Użytkownika: " + element.getUsersId()
                            + " Miesiąc: " + element.getMonth()
                            + " Liczba zamówień: " + element.getNumberOfOrders()
                    ));
        }
    }
}
