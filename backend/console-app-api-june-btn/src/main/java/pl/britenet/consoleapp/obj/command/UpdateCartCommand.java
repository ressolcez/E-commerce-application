package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.CartBuilder;
import pl.britenet.consoleapp.obj.model.Cart;
import pl.britenet.consoleapp.service.CartService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UpdateCartCommand extends Command{

    private final CartService cartService;

    public UpdateCartCommand(CartService cartService) {
        super(Constants.COMMAND_UPDATE_CART);
        this.cartService = cartService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        try{


        System.out.println("Wprowadź ID koszyka do modyfikacji:");

            int id;

            while(true){
                try{
                    id = Integer.parseInt(scanner.next());
                    break;
                }catch (NumberFormatException e){
                    System.out.println("Podaj poprawna liczbe");
                }
            }

        if(this.cartService.getCart(id).isEmpty()){
            System.out.println("Nie ma takiego Id");
            return;
        }

        System.out.println("Wprowadź ID użytkownika:");
        int userId = scanner.nextInt();

        System.out.println("Wprowadź cene koszyka:");
        double totalPrice = scanner.nextDouble();

        Cart cart = new CartBuilder(id)
                .setUsersId(userId)
                .setTotalPrice(totalPrice)
                .getCart();
        this.cartService.updateCart(cart);
        System.out.println("Koszyk został zmodyfkowany");

        }catch (IllegalStateException | InputMismatchException e){
            if(e.getMessage() == null){
                System.out.println("Wpisano niepoprawne dane");
            }else {
                System.out.println("Wpisano niepoprawne dane: " + e.getMessage());
            }
        }
    }
}
