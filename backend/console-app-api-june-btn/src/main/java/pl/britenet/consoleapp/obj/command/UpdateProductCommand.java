package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.ProductBuilder;
import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.service.ProductService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UpdateProductCommand extends Command{

    private final ProductService productService;

    public UpdateProductCommand(ProductService productService) {
        super(Constants.COMMAND_UPDATE_PRODUCT);
        this.productService = productService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        try{
        System.out.println("Wprowadź ID produktu do zmodyfikowania:");
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

        System.out.println("Wprowadź nazwę produktu:");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Wprowadź opis produktu:");
        String description = scanner.nextLine();
        System.out.println("Wprowadź cenę produktu:");
        double price = scanner.nextDouble();
        System.out.println("Wprowadź zdjęcie produktu:");
        String image = scanner.nextLine();

        Product product = new ProductBuilder(id)
                .setName(name)
                .setDescription(description)
                .setPrice(price)
                .setImage(image)
                .getProduct();

        this.productService.updateProduct(product);
        System.out.println("Produkt został zmodyfikowany!");

        }catch (IllegalStateException | InputMismatchException e){
            if(e.getMessage() == null){
                System.out.println("Wpisano niepoprawne dane");
            }else {
                System.out.println("Wpisano niepoprawne dane: " + e.getMessage());
            }
        }
    }
}
