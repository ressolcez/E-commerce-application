package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.OrderProductBuilder;
import pl.britenet.consoleapp.obj.model.OrderProduct;
import pl.britenet.consoleapp.service.OrderProductService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class InsertOrderProductCommand extends Command{
    private final OrderProductService orderProductService;

    public InsertOrderProductCommand(OrderProductService orderProductService) {
        super(Constants.COMMAND_INSERT_ORDERPRODUCT);
        this.orderProductService = orderProductService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        try{
        System.out.println("Wprowadź ID Zamówienia:");
        int ordersId = scanner.nextInt();

        System.out.println("Wprowadź ID Produktu:");
        int productId = scanner.nextInt();

        System.out.println("Wprowadź ilość:");
        int quantity = scanner.nextInt();

        System.out.println("Wprowadź Cene:");
        double price = scanner.nextDouble();


        OrderProduct orderProduct = new OrderProductBuilder()
                .setOrdersId(ordersId)
                .setProductId(productId)
                .setQuantity(quantity)
                .setPrice(price)
                .getOrderProduct();

        this.orderProductService.insertOrderProduct(orderProduct);

        System.out.println("Poprawnie dodano dane do OrderProduct");

        }catch (IllegalStateException | InputMismatchException e){
            if(e.getMessage() == null){
                System.out.println("Wpisano niepoprawne dane");
            }else {
                System.out.println("Wpisano niepoprawne dane: " + e.getMessage());
            }
        }
    }
}
