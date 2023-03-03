package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;

import pl.britenet.consoleapp.obj.model.Cart;
import pl.britenet.consoleapp.service.CartService;
import pl.britenet.consoleapp.service.PaginationService;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;

public class GetAllCartCommand extends Command{

    private final CartService cartService;

    public GetAllCartCommand(CartService cartService) {
        super(Constants.COMMAND_GET_ALLCART);
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        int amount;

        Collection<Cart> allRows = this.cartService.getAllCart().get();
        System.out.println("Podaj ilość wierszy");

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

        PaginationService<Cart> paginationService = new PaginationService<>(allRows,amount);

        ON = true;

        while(ON) {
            System.out.println("Co chcesz zrobić? (Help wyświetla wszystkie komendy)");
            String command = scanner.nextLine();
            paginationService.PaginationMenu(command).forEach(element ->
                    System.out.println("Id koszyka: " + element.getId()
                            + " Id użytkownika: " + element.getUsersId()
                            + " Cena: " + element.getTotalPrice()
                    ));
        }
    }
}
