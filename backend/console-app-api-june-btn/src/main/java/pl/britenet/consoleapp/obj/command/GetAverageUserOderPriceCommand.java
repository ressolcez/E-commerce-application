package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.AverageUserOrderPrice;
import pl.britenet.consoleapp.obj.model.Users;
import pl.britenet.consoleapp.service.AverageUserOderPriceService;
import pl.britenet.consoleapp.service.PaginationService;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetAverageUserOderPriceCommand extends Command{

    private final AverageUserOderPriceService averageUserOderPriceService;

    public GetAverageUserOderPriceCommand(AverageUserOderPriceService averageUserOderPriceService) {
        super(Constants.COMMAND_GET_AVERAGEUSERORDERPRICE);
        this.averageUserOderPriceService = averageUserOderPriceService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        Collection<AverageUserOrderPrice> allRows = this.averageUserOderPriceService.getAllRows().get();

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

        PaginationService<AverageUserOrderPrice> paginationService = new PaginationService<>(allRows,amount);

        ON = true;

        while(ON) {
            System.out.println("Co chcesz zrobić? (Help wyświetla wszystkie komendy)");
            String command = scanner.nextLine();
            paginationService.PaginationMenu(command).forEach(element ->
                    System.out.println("Id użytkownika: " + element.getUsersId()
                            + " Imie użytkownika: " + element.getName()
                            + " Nazwisko użytkownika: " + element.getSurname()
                            + " Telefon użytkownika: " + element.getPhone()
                            + " Średnia cena zamówień: " + element.getAvgPriceOrder()
                    ));
        }
    }
}
