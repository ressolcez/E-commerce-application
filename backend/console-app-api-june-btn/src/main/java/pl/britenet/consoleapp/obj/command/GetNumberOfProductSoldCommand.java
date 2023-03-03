package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.NumberOfProductSold;
import pl.britenet.consoleapp.obj.model.Users;
import pl.britenet.consoleapp.service.NumberOfProductSoldService;
import pl.britenet.consoleapp.service.PaginationService;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetNumberOfProductSoldCommand extends Command{

    private final NumberOfProductSoldService numberOfProductSoldService;

    public GetNumberOfProductSoldCommand(NumberOfProductSoldService numberOfProductSoldService) {
        super(Constants.COMMAND_GET_NUMBEROFPRODUCTSOLD);
        this.numberOfProductSoldService = numberOfProductSoldService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        Collection<NumberOfProductSold> allRows = this.numberOfProductSoldService.getAllRows().get();
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

        PaginationService<NumberOfProductSold> paginationService = new PaginationService<>(allRows,amount);

        ON = true;

        while(ON) {
            System.out.println("Co chcesz zrobić? (Help wyświetla wszystkie komendy)");
            String command = scanner.nextLine();
            paginationService.PaginationMenu(command).forEach(numberOfProductSolds1 ->
                    System.out.println("Id Przedmiotu: " + numberOfProductSolds1.getProductId()
                            + " Nazwa przedmiotu: " + numberOfProductSolds1.getName()
                            + " Liczba sprzedanych sztuk przedmiotu: " + numberOfProductSolds1.getNumberOfSold()
                    ));
        }
    }
}
