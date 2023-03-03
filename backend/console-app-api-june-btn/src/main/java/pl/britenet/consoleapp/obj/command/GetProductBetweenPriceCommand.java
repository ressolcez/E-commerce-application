package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.NumberOfProductSold;
import pl.britenet.consoleapp.obj.model.ProductBetweenPrice;
import pl.britenet.consoleapp.service.PaginationService;
import pl.britenet.consoleapp.service.ProductBetweenPriceService;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetProductBetweenPriceCommand extends Command{
    private final ProductBetweenPriceService productBetweenPriceService;

    public GetProductBetweenPriceCommand(ProductBetweenPriceService productBetweenPriceService) {
        super(Constants.COMMAND_GET_PRODUCTBETWEENPRICE);
        this.productBetweenPriceService = productBetweenPriceService;
    }

    @Override
    public void execute() {


        Scanner scanner = new Scanner(System.in);

        Collection<ProductBetweenPrice> allRows = this.productBetweenPriceService.getAllRows().get();
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

        PaginationService<ProductBetweenPrice> paginationService = new PaginationService<>(allRows,amount);

        ON = true;

        System.out.println("Produkty z przedziału cenowego:");

        while(ON) {
            System.out.println("Co chcesz zrobić?");
            String command = scanner.nextLine();
            paginationService.PaginationMenu(command).forEach(element ->
                    System.out.println("Id Produktu: " + element.getId()
                            + " Nazwa produktu: " + element.getName()
                            + " Cena: " + element.getPrice()
                    ));
        }
    }
}
