package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Cart;
import pl.britenet.consoleapp.service.CartService;

import java.util.Optional;
import java.util.Scanner;

public class GetCartCommand extends Command{
    private final CartService cartService;

    public GetCartCommand(CartService cartService) {
        super(Constants.COMMAND_GET_CART);
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID koszyka:");
        int id;

        while(true){
            try{
                id = Integer.parseInt(scanner.next());
                break;
            }catch (NumberFormatException e){
                System.out.println("Podaj poprawna liczbe");
            }
        }

        Optional<Cart> cart = this.cartService.getCart(id);

        if(cart.isPresent()){

            System.out.println("Id koszyka: " + cart.get().getId());
            System.out.println("Id użytkownika: " + cart.get().getUsersId());
            System.out.println("Cena koszyka: " + cart.get().getTotalPrice());

        }else{
            System.out.println("Nie ma Koszyka o takim ID");
        }

    }
}
