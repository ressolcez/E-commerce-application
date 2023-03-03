package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.CartProductBuilder;
import pl.britenet.consoleapp.obj.model.CartProduct;
import pl.britenet.consoleapp.service.CartProductService;

import java.util.Scanner;

public class DeleteCartProductCommand extends Command{

    private final CartProductService cartProductService;

    public DeleteCartProductCommand(CartProductService cartProductService) {
        super(Constants.COMMAND_DELETE_CARTPRODUCT);
        this.cartProductService = cartProductService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID CartProduct:");
        int id;

        while(true){
            try{
                id = Integer.parseInt(scanner.next());
                break;
            }catch (NumberFormatException e){
                System.out.println("Podaj liczbe");
            }
        }


        if(this.cartProductService.getCartProduct(id).isEmpty()){
            System.out.println("Nie ma takiego Id");
            return;
        }

        CartProduct cartProduct= new CartProductBuilder(id).getCartProduct();

        try{
        this.cartProductService.deleteCartProduct(cartProduct);

        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Usunięto CartProduct");
    }
}
