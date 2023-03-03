package pl.britenet.campusspringjune.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.britenet.consoleapp.service.*;

@Configuration
public class APIConfig {

    private final DatabaseService databaseService;

    @Autowired
    public APIConfig(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Bean
    public ProductService getProductService() {
        return new ProductService(this.databaseService);
    }

    @Bean
    public UsersService getUserService() {
        return new UsersService(this.databaseService);
    }

    @Bean
    public OrdersService getOrdersService() {
        return new OrdersService(this.databaseService);
    }

    @Bean
    public OrderProductService getOrderProductService() {
        return new OrderProductService(this.databaseService);
    }

    @Bean
    public CartProductService getCartProductService() {
        return new CartProductService(this.databaseService);
    }

    @Bean
    public CartService getCartService() {
        return new CartService(this.databaseService);
    }

    @Bean
    public ProductInCartService getProductInCart() {
        return new ProductInCartService(this.databaseService);
    }

    @Bean
    public ProductInOrderService getOrderProduct() {
        return new ProductInOrderService(this.databaseService);
    }

}
