package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.CartProductBuilder;
import pl.britenet.consoleapp.obj.model.CartProduct;
import pl.britenet.consoleapp.service.CartProductService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class InsertCartProductCommand extends Command{

    private final CartProductService cartProductService;

    public InsertCartProductCommand(CartProductService cartProductService) {
        super(Constants.COMMAND_INSERT_CARTPRODUCT);
        this.cartProductService = cartProductService;
    }


    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        try{
        System.out.println("Wprowadź id koszyka:");
        int cartId = scanner.nextInt();

        System.out.println("Wprowadź id produkt:");
        int productId = scanner.nextInt();

        System.out.println("Wprowadź ilość:");
        int quantity = scanner.nextInt();

        CartProduct cartProduct = new CartProductBuilder()
                .setCartId(cartId)
                .setProductId(productId)
                .setQuantity(quantity)
                .getCartProduct();

        this.cartProductService.insertCartProduct(cartProduct, "+");

        System.out.println("Poprawnie dodano dane do CartProduct");

        }catch (IllegalStateException | InputMismatchException e){
            if(e.getMessage() == null){
                System.out.println("Wpisano niepoprawne dane");
            }else {
                System.out.println("Wpisano niepoprawne dane: " + e.getMessage());
            }
        }
    }
}
