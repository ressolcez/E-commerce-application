package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.NumberOfProductSold;
import pl.britenet.consoleapp.obj.model.NumberProductCart;
import pl.britenet.consoleapp.service.NumberProductCartService;
import pl.britenet.consoleapp.service.PaginationService;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetNumberProductCartCommand extends Command{

    private final NumberProductCartService numberProductCartService;

    public GetNumberProductCartCommand(NumberProductCartService numberProductCartService) {
        super(Constants.COMMAND_GET_NUMBERPRODUCTCART);
        this.numberProductCartService = numberProductCartService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        Collection<NumberProductCart> allRows = this.numberProductCartService.getAllRows().get();
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

        PaginationService<NumberProductCart> paginationService = new PaginationService<>(allRows,amount);

        ON = true;

        System.out.println("Liczba produktów w koszykach poszczegolnych uzytkownikow");

        while(ON) {
            System.out.println("Co chcesz zrobić? (Help wyświetla wszystkie komendy)");
            String command = scanner.nextLine();
            paginationService.PaginationMenu(command).forEach(element ->
                    System.out.println("Id Użytkownika: " + element.getUsersId()
                            + " Imie użytkownika: " + element.getName()
                            + " Nazwisko użytkownika: " + element.getSurname()
                            + " Liczba przedmiotów w koszyku " + element.getNumberOfProducts()
                    ));
        }
    }
}
