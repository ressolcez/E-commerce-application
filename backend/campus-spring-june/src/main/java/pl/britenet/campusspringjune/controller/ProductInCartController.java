package pl.britenet.campusspringjune.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.consoleapp.obj.model.ProductInCart;
import pl.britenet.consoleapp.service.ProductInCartService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/productInCart")
@CrossOrigin("*")
public class ProductInCartController {

    private final ProductInCartService productInCartService;

    @Autowired
    public ProductInCartController(ProductInCartService productInCartService) {
        this.productInCartService = productInCartService;
    }

    @GetMapping("/{userId}")
    public Optional<Collection<ProductInCart>> getProductInCart(@PathVariable int userId) {
        return this.productInCartService.getProductInCart(userId);
    }

}
