package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.OrderProductBuilder;
import pl.britenet.consoleapp.obj.model.OrderProduct;
import pl.britenet.consoleapp.service.OrderProductService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UpdateOrderProduct extends Command{

    private final OrderProductService orderProductService;

    public UpdateOrderProduct(OrderProductService orderProductService) {
        super(Constants.COMMAND_UPDATE_ORDERPRODUCT);
        this.orderProductService = orderProductService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        try{


        System.out.println("Wprowadź ID OrderProduct do modyfikacji:");
            int id;

            while(true){
                try{
                    id = Integer.parseInt(scanner.next());
                    break;
                }catch (NumberFormatException e){
                    System.out.println("Podaj poprawna liczbe");
                }
            }

        if(this.orderProductService.getOrderProduct(id).isEmpty()){
            System.out.println("Nie ma takiego Id");
            return;
        }

        System.out.println("Wprowadź ID Zamówienia:");
        int ordersId = scanner.nextInt();

        System.out.println("Wprowadź ID Produktu:");
        int productId = scanner.nextInt();

        System.out.println("Wprowadź ilość:");
        int quantity = scanner.nextInt();

        System.out.println("Wprowadź Cene:");
        double price = scanner.nextDouble();

        OrderProduct orderProduct = new OrderProductBuilder(id)
                .setOrdersId(ordersId)
                .setProductId(productId)
                .setQuantity(quantity)
                .setPrice(price)
                .getOrderProduct();

        this.orderProductService.updateOrderProduct(orderProduct);

        System.out.println("Poprawnie zmodyfikowano dane w OrderProduct");

        }catch (IllegalStateException | InputMismatchException e){
            if(e.getMessage() == null){
                System.out.println("Wpisano niepoprawne dane");
            }else {
                System.out.println("Wpisano niepoprawne dane: " + e.getMessage());
            }
        }
    }
}
