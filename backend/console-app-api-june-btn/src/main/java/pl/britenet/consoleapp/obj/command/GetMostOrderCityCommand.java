package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.MostOrdersCity;
import pl.britenet.consoleapp.obj.model.Users;
import pl.britenet.consoleapp.service.MostOrdersCityService;
import pl.britenet.consoleapp.service.PaginationService;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetMostOrderCityCommand extends Command {

    private final MostOrdersCityService mostOrdersCityService;

    public GetMostOrderCityCommand(MostOrdersCityService mostOrdersCityService) {
        super(Constants.COMMAND_GET_MOSTORDERCITY);
        this.mostOrdersCityService = mostOrdersCityService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        Collection<MostOrdersCity> allRows = this.mostOrdersCityService.getAllRows().get();
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

        PaginationService<MostOrdersCity> paginationService = new PaginationService<>(allRows,amount);

        ON = true;

        while(ON) {
            System.out.println("Co chcesz zrobić?");
            String command = scanner.nextLine();
            paginationService.PaginationMenu(command).forEach(mostOrdersCities1 ->
                    System.out.println("Address: " + mostOrdersCities1.getAddress()
                            + " Ilość zamówień: " + mostOrdersCities1.getNumber_of_orders()
                    ));
        }
    }
}
