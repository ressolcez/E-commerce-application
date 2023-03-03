package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.CartProductBuilder;
import pl.britenet.consoleapp.obj.model.CartProduct;
import pl.britenet.consoleapp.service.CartProductService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UpdateCartProductCommand extends Command{

    private final CartProductService cartProductService;

    public UpdateCartProductCommand(CartProductService cartProductService) {
        super(Constants.COMMAND_UPDATE_CARTPRODUCT);
        this.cartProductService = cartProductService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        try{
        System.out.println("Wprowadź ID produktu do modyfikacji:");
        int id;

            while(true){
                try{
                    id = Integer.parseInt(scanner.next());
                    break;
                }catch (NumberFormatException e){
                    System.out.println("Podaj poprawna liczbe");
                }
            }

        if(this.cartProductService.getCartProduct(id).isEmpty()){
            System.out.println("Nie ma takiego Id");
            return;
        }

        System.out.println("Wprowadź id koszyka:");
        int cartId = scanner.nextInt();

        System.out.println("Wprowadź id produkt:");
        int productId = scanner.nextInt();

        System.out.println("Wprowadź ilość:");
        int quantity = scanner.nextInt();

        CartProduct cartProduct = new CartProductBuilder(id)
                .setCartId(cartId)
                .setProductId(productId)
                .setQuantity(quantity)
                .getCartProduct();

        this.cartProductService.updateCartProduct(cartProduct);

        System.out.println("Poprawnie zmodyfikowano dane do tabeli CartProduct");

        }catch (IllegalStateException | InputMismatchException e){
            if(e.getMessage() == null){
                System.out.println("Wpisano niepoprawne dane");
            }else {
                System.out.println("Wpisano niepoprawne dane: " + e.getMessage());
            }
        }

    }
}
