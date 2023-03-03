package pl.britenet.campusspringjune.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.consoleapp.obj.model.OrderProduct;
import pl.britenet.consoleapp.service.OrderProductService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orderProduct")
@CrossOrigin("*")
public class OrderProductController {
    private final OrderProductService orderProductService;

    @Autowired
    public OrderProductController(OrderProductService OrderProductService) {
        this.orderProductService = OrderProductService;
    }

    @GetMapping
    public Optional<Collection<OrderProduct>> getAllOrderProduct() {
        return this.orderProductService.getAllOrderProduct();
    }

    @GetMapping("/{orderProductId}")
    public Optional<OrderProduct> getOrderProduct(@PathVariable int orderProductId) {
        return this.orderProductService.getOrderProduct(orderProductId);
    }

    @PostMapping
    public void insertOrderProduct(@RequestBody OrderProduct orderProduct) {

        this.orderProductService.insertOrderProduct(orderProduct);
    }

    @PutMapping
    public void updateOrderProduct(@RequestBody OrderProduct orderProduct) {
        this.orderProductService.updateOrderProduct(orderProduct);
    }

    @DeleteMapping("/{OrderProductId}")
    public void deleteOrderProduct(@PathVariable int orderProductId) {
        this.orderProductService.deleteOrderProduct(new OrderProduct(orderProductId));
    }
}