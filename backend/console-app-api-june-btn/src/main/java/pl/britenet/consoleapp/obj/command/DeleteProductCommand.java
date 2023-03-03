package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.ProductBuilder;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.service.ProductService;

import java.util.Optional;
import java.util.Scanner;

public class DeleteProductCommand extends Command{

    private final ProductService productService;

    public DeleteProductCommand(ProductService productService) {
        super(Constants.COMMAND_DELETE_PRODUCT);
        this.productService = productService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID produktu:");

        int id;

        while(true){
            try{
                id = Integer.parseInt(scanner.next());
                break;
            }catch (NumberFormatException e){
                System.out.println("Podaj poprawna liczbe");
            }
        }

        if(this.productService.getProduct(id).isEmpty()){
            System.out.println("Nie ma takiego Id");
            return;
        }

        Product product = new ProductBuilder(id).getProduct();
        try {

            this.productService.deleteProduct(product);

        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Usunięto produkt");
    }
}
