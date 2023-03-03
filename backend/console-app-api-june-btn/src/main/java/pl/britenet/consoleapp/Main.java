package pl.britenet.consoleapp;

import com.mysql.cj.exceptions.CJCommunicationsException;
import pl.britenet.consoleapp.obj.command.*;
import pl.britenet.consoleapp.service.*;

import java.net.ConnectException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static boolean IS_ON = true;
    public static void main(String[] args) {

        DatabaseService databaseService = null;

        try {
            databaseService = new DatabaseService();
        } catch (ConnectException e) {
            System.out.println(e.getMessage());
            IS_ON = false;
        }

        ProductService productService = new ProductService(databaseService);
        UsersService usersService = new UsersService(databaseService);
        OrdersService ordersService = new OrdersService(databaseService);
        CartProductService cartProductService = new CartProductService(databaseService);
        CartService cartService = new CartService(databaseService);
        OrderProductService orderProductService = new OrderProductService(databaseService);
        NumberOfOrdersService numberOfOrdersService = new NumberOfOrdersService(databaseService);
        ProductBetweenPriceService productBetweenPriceService = new ProductBetweenPriceService(databaseService);
        MostOrdersCityService mostOrdersCityService = new MostOrdersCityService(databaseService);
        OrderWithAddressService orderWithAddressService = new OrderWithAddressService(databaseService);
        AverageUserOderPriceService averageUserOderPriceService = new AverageUserOderPriceService(databaseService);
        UserMostOrdersService userMostOrdersService = new UserMostOrdersService(databaseService);
        NumberProductCartService numberProductCartService = new NumberProductCartService(databaseService);
        UserMostOrderMonthService userMostOrderMonthService = new UserMostOrderMonthService(databaseService);
        NumberOrdersDateService numberOrdersDateService = new NumberOrdersDateService(databaseService);
        NumberOfProductSoldService numberOfProductSoldService = new NumberOfProductSoldService(databaseService);
        ProductInCartService productInCartService = new ProductInCartService(databaseService);

        CommandService commandService = new CommandService();

        commandService.registerCommand(new HelpCommand(commandService));
        commandService.registerCommand(new ExitCommand());

        commandService.registerCommand(new InsertProductCommand(productService));
        commandService.registerCommand(new GetProductCommand(productService));
        commandService.registerCommand(new UpdateProductCommand(productService));
        commandService.registerCommand(new DeleteProductCommand(productService));
        commandService.registerCommand(new GetAllProductsCommand(productService));

        commandService.registerCommand(new InsertUsersCommand(usersService));
        commandService.registerCommand(new GetUsersCommand(usersService));
        commandService.registerCommand(new UpdateUsersCommand(usersService));
        commandService.registerCommand(new DeleteUsersCommand(usersService));
        commandService.registerCommand(new GetAllUsersCommand(usersService));

        commandService.registerCommand(new InsertOrdersCommand(ordersService));
        commandService.registerCommand(new GetOrdersCommand(ordersService));
        commandService.registerCommand(new DeleteOrdersCommand(ordersService));
        commandService.registerCommand(new UpdateOrdersCommand(ordersService));
        commandService.registerCommand(new GetAllOrdersCommand(ordersService));

        commandService.registerCommand(new InsertCartProductCommand(cartProductService));
        commandService.registerCommand(new GetCartProductCommand(cartProductService));
        commandService.registerCommand(new UpdateCartProductCommand(cartProductService));
        commandService.registerCommand(new DeleteCartProductCommand(cartProductService));
        commandService.registerCommand(new GetAllCartProductCommand(cartProductService));

        commandService.registerCommand(new InsertCartCommand(cartService));
        commandService.registerCommand(new GetCartCommand(cartService));
        commandService.registerCommand(new DeleteCartCommand(cartService));
        commandService.registerCommand(new UpdateCartCommand(cartService));
        commandService.registerCommand(new GetAllCartCommand(cartService));

        commandService.registerCommand(new InsertOrderProductCommand(orderProductService));
        commandService.registerCommand(new GetOrderProductCommand(orderProductService));
        commandService.registerCommand(new DeleteOrderProductCommand(orderProductService));
        commandService.registerCommand(new UpdateOrderProduct(orderProductService));
        commandService.registerCommand(new GetAllOrderProductCommand(orderProductService));

        commandService.registerCommand(new GetNumberOfOrdersCommand(numberOfOrdersService));
        commandService.registerCommand(new GetProductBetweenPriceCommand(productBetweenPriceService));
        commandService.registerCommand(new GetMostOrderCityCommand(mostOrdersCityService));
        commandService.registerCommand(new GetOrderWithAddressCommand(orderWithAddressService));
        commandService.registerCommand(new GetAverageUserOderPriceCommand(averageUserOderPriceService));
        commandService.registerCommand(new GetUsersMostOrdersCommand(userMostOrdersService));
        commandService.registerCommand(new GetNumberProductCartCommand(numberProductCartService));
        commandService.registerCommand(new GetUserMostOrderMonthCommand(userMostOrderMonthService));
        commandService.registerCommand(new GetNumberOrdersDateCommand(numberOrdersDateService));
        commandService.registerCommand(new GetNumberOfProductSoldCommand(numberOfProductSoldService));

        Scanner scanner = new Scanner(System.in);

        while (IS_ON) {

            System.out.println("Podaj komende: (help wyświetla wszystkie komendy)");
            String commandName = scanner.nextLine();
            Optional<Command> optionalCommand = commandService.findCommand(commandName);

            if (optionalCommand.isPresent()) {
                optionalCommand.get().execute();
            }
            else {
                System.out.println("Nieznana komenda");
            }
        }
    }
}