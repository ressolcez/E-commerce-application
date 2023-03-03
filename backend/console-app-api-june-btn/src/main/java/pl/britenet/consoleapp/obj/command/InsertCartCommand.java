package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.CartBuilder;
import pl.britenet.consoleapp.obj.model.Cart;
import pl.britenet.consoleapp.service.CartService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class InsertCartCommand extends Command{

    private final CartService cartService;

    public InsertCartCommand(CartService cartService) {
        super(Constants.COMMAND_INSERT_CART);
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        try{
        System.out.println("Wprowadź ID użytkownika:");
        int userId = scanner.nextInt();

        System.out.println("Wprowadź cene koszyka:");
        double totalPrice = scanner.nextDouble();

        Cart cart = new CartBuilder()
                .setUsersId(userId)
                .setTotalPrice(totalPrice)
                .getCart();

        this.cartService.insertCart(cart);
        System.out.println("Koszyk został utworzony!");

        }catch (IllegalStateException | InputMismatchException e){
            if(e.getMessage() == null){
                System.out.println("Wpisano niepoprawne dane");
            }else {
                System.out.println("Wpisano niepoprawne dane: " + e.getMessage());
            }
        }
    }
}
