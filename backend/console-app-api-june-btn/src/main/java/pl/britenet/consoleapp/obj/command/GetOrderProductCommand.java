package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.OrderProduct;
import pl.britenet.consoleapp.service.OrderProductService;

import java.util.Optional;
import java.util.Scanner;

public class GetOrderProductCommand extends Command{

    private final OrderProductService orderProductService;

    public GetOrderProductCommand(OrderProductService orderProductService) {
        super(Constants.COMMAND_GET_ORDERPRODUCT);
        this.orderProductService = orderProductService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID OrderProduct:");
        int id;

        while(true){
            try{
                id = Integer.parseInt(scanner.next());
                break;
            }catch (NumberFormatException e){
                System.out.println("Podaj poprawna liczbe");
            }
        }

        Optional<OrderProduct> orderProduct = this.orderProductService.getOrderProduct(id);

        if(orderProduct.isPresent()){

            System.out.println("Id orderProduct: " + orderProduct.get().getId());
            System.out.println("Id zamówienia: " + orderProduct.get().getOrdersId());
            System.out.println("Id produktu: " + orderProduct.get().getProductId());
            System.out.println("Ilość: " + orderProduct.get().getQuantity());
            System.out.println("Cena: " + orderProduct.get().getPrice());

        }else{
            System.out.println("Nie ma OrderProduct o takim ID");
        }
    }
}
