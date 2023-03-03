package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.OrderProductBuilder;
import pl.britenet.consoleapp.obj.model.OrderProduct;
import pl.britenet.consoleapp.service.OrderProductService;

import java.util.Scanner;

public class DeleteOrderProductCommand extends Command{

    private final OrderProductService orderProductService;

    public DeleteOrderProductCommand(OrderProductService orderProductService) {
        super(Constants.COMMAND_DELETE_ORDERPRODUCT);
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

        if(this.orderProductService.getOrderProduct(id).isEmpty()){
            System.out.println("Nie ma takiego Id");
            return;
        }

        OrderProduct orderProduct = new OrderProductBuilder(id).getOrderProduct();

        try {
            this.orderProductService.deleteOrderProduct(orderProduct);
        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Usunięto OrderProduct");
    }
}
