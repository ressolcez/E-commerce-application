package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.service.ProductService;

import java.util.Optional;
import java.util.Scanner;

public class GetProductCommand extends Command{

    private final ProductService productService;

    public GetProductCommand(ProductService productService) {
        super(Constants.COMMAND_GET_PRODUCT);
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

        Optional<Product> product = this.productService.getProduct(id);

        if(product.isPresent()){
            System.out.println("Id produktu: " + product.get().getId());
            System.out.println("Nazwa produktu: " + product.get().getName());
            System.out.println("Opis produktu: " + product.get().getDescription());
            System.out.println("Cena produktu: " + product.get().getPrice());
            System.out.println("Zdjęcie produktu: " + product.get().getImage());

        }else{
            System.out.println("Nie ma produktu o takim ID");
        }

    }
}
