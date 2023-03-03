package pl.britenet.consoleapp.obj.command;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.OrderProduct;
import pl.britenet.consoleapp.service.OrderProductService;
import pl.britenet.consoleapp.service.PaginationService;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static pl.britenet.consoleapp.Constants.ON;


public class GetAllOrderProductCommand extends Command{

    private final OrderProductService orderProductService;

    public GetAllOrderProductCommand(OrderProductService orderProductService) {
        super(Constants.COMMAND_GET_ALLORDERPRODUCT);
        this.orderProductService = orderProductService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        Collection<OrderProduct> allRows = this.orderProductService.getAllOrderProduct().get();
        System.out.println("Podaj ilość wierszy");

        int amount;

        while(true){
            try{
                amount = Integer.parseInt(scanner.next());
                break;
            }catch (NumberFormatException e){
                System.out.println("Podaj poprawna liczbe");
            }
        }

        if(amount < 1){
            System.out.println("Liczba rekordów musi być większa od 0");
            return;
        }


        scanner.nextLine();

        PaginationService<OrderProduct> paginationService = new PaginationService<>(allRows,amount);

        ON = true;

        while(ON) {
            System.out.println("Co chcesz zrobić? (Help wyświetla wszystkie komendy)");
            String command = scanner.nextLine();
            paginationService.PaginationMenu(command).forEach((element ->
                    System.out.println("Id OrderProduct: " + element.getId()
                            + " Id zamówienia: " + element.getOrdersId()
                            + " Id produktu: " + element.getProductId()
                            + " Ilość: " + element.getQuantity()
                            + " Cena: " + element.getPrice()
            )));
        }
    }
}
