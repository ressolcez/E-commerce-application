package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.builder.OrdersBuilder;
import pl.britenet.consoleapp.obj.model.Orders;
import pl.britenet.consoleapp.service.OrdersService;

import java.util.Scanner;

public class DeleteOrdersCommand extends Command{

    private final OrdersService ordersService;

    public DeleteOrdersCommand(OrdersService ordersService) {
        super(Constants.COMMAND_DELETE_ORDERS);
        this.ordersService = ordersService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadź ID zamówienia:");
        int id;

        while(true){
            try{
                id = Integer.parseInt(scanner.next());
                break;
            }catch (NumberFormatException e){
                System.out.println("Podaj poprawna liczbe");
            }
        }

        if(this.ordersService.getOrders(id).isEmpty()){
            System.out.println("Nie ma takiego Id");
            return;
        }

        Orders orders = new OrdersBuilder(id).getOrders();

        try {
            this.ordersService.deleteOrders(orders);
        }catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Usunięto zamówienie");
    }
}
