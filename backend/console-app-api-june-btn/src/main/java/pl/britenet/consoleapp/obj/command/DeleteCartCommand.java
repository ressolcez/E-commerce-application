package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.CartBuilder;
import pl.britenet.consoleapp.obj.model.Cart;
import pl.britenet.consoleapp.service.CartService;

import java.util.Scanner;

public class DeleteCartCommand extends Command{

    private final CartService cartService;

    public DeleteCartCommand(CartService cartService) {
        super(Constants.COMMAND_DELETE_CART);
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

        if(this.cartService.getCart(id).isEmpty()){
            System.out.println("Nie ma takiego Id");
            return;
        }

        Cart cart = new CartBuilder(id).getCart();

        try{

        this.cartService.deleteCart(cart);

        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Usunięto koszyk");
    }
}
