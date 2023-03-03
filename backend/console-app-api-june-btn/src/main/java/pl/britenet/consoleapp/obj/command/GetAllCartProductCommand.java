package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.CartProduct;
import pl.britenet.consoleapp.obj.model.Users;
import pl.britenet.consoleapp.service.CartProductService;
import pl.britenet.consoleapp.service.PaginationService;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;


public class GetAllCartProductCommand extends Command{

    private final CartProductService cartProductService;

    public GetAllCartProductCommand(CartProductService cartProductService) {
        super(Constants.COMMAND_GET_ALLCARTPRODUCT);
        this.cartProductService = cartProductService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        Collection<CartProduct> allRows = this.cartProductService.getAllCartProduct().get();
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

        PaginationService<CartProduct> paginationService = new PaginationService<>(allRows, amount);

        ON = true;

        while (ON) {
            System.out.println("Co chcesz zrobić? (Help wyświetla wszystkie komendy)");
            String command = scanner.nextLine();
            paginationService.PaginationMenu(command).forEach(element ->
                    System.out.println("Id CartProduct " + element.getId()
                            + " Id koszyka: " + element.getCartId()
                            + " Id produktu: " + element.getProductId()
                            + " Ilość: " + element.getQuantity()
                    ));
        }
    }
}
