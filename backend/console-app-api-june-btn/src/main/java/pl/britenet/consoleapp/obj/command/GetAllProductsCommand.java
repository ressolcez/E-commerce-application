package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.obj.model.Users;
import pl.britenet.consoleapp.service.PaginationService;
import pl.britenet.consoleapp.service.ProductService;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetAllProductsCommand extends Command{

    private final ProductService productService;
    public static boolean IS_ON = true;
    public GetAllProductsCommand(ProductService productService) {
        super(Constants.COMMAND_GETALLPRODUCTS);
        this.productService = productService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        Collection<Product> allRows = this.productService.getAllProducts().get();
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



        PaginationService<Product> paginationService = new PaginationService<>(allRows,amount);

        scanner.nextLine();

        ON = true;

        while(ON) {
            System.out.println("Co chcesz zrobić? (Help wyświetla wszystkie komendy)");
            String command = scanner.nextLine();
            paginationService.PaginationMenu(command).forEach(element ->
                    System.out.println("Id produktu: " + element.getId()
                            + " Nazwa produktu: " + element.getName()
                            + " Opis produktu: "+ element.getDescription()
                            + " Cena produktu: "+ element.getPrice()
                            + " Zdjęcie produktu: "+ element.getImage()
                    ));
            }
    }
}
