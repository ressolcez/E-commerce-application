package pl.britenet.campusspringjune.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.consoleapp.obj.model.ProductInOrder;
import pl.britenet.consoleapp.service.ProductInOrderService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/productInOrder")
@CrossOrigin("*")
public class ProductInOrderController {
    private final ProductInOrderService productInOrderService;

    @Autowired
    public ProductInOrderController(ProductInOrderService productInOrderService) {
        this.productInOrderService = productInOrderService;
    }

    @GetMapping("/{orderId}")
    public Optional<Collection<ProductInOrder>> getProductInOrder(@PathVariable int orderId) {
        return this.productInOrderService.getProductInOrder(orderId);
    }
}