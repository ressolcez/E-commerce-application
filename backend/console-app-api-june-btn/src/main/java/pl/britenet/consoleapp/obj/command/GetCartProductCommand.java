package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.CartProduct;
import pl.britenet.consoleapp.service.CartProductService;

import java.util.Optional;
import java.util.Scanner;

public class GetCartProductCommand extends Command {

    private final CartProductService cartProductService;

    public GetCartProductCommand(CartProductService cartProductService) {
        super(Constants.COMMAND_GET_CARTPRODUCT);
        this.cartProductService = cartProductService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID tabeli CartProduct:");
        int id;

        while(true){
            try{
                id = Integer.parseInt(scanner.next());
                break;
            }catch (NumberFormatException e){
                System.out.println("Podaj poprawna liczbe");
            }
        }

        Optional<CartProduct> cartProduct = this.cartProductService.getCartProduct(id);

        if(cartProduct.isPresent()){
            System.out.println("Id tabeli CartProduct: " + cartProduct.get().getId());
            System.out.println("Id koszyka: " + cartProduct.get().getCartId());
            System.out.println("Id produkt: " + cartProduct.get().getProductId());
            System.out.println("Ilość: " + cartProduct.get().getQuantity());
        }else{
            System.out.println("Nie ma CartProduct o takim ID");
        }

    }
}
