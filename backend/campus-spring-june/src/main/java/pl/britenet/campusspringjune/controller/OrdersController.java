package pl.britenet.campusspringjune.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.consoleapp.obj.model.Orders;
import pl.britenet.consoleapp.service.OrdersService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin("*")
public class OrdersController {
    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping
    public Optional<Collection<Orders>> getAllOrders() {
        return this.ordersService.getAllOrders();
    }

    @GetMapping("/{ordersId}")
    public Optional<Orders> getOrders(@PathVariable int ordersId) {
        return this.ordersService.getOrders(ordersId);
    }

    @GetMapping("/OrdersUser/{usersId}")
    public Optional<Collection<Orders>> getOrdersUser(@PathVariable int usersId) {
        return this.ordersService.getAllOrdersUsers(usersId);
    }

    @PostMapping
    public void insertOrders(@RequestBody Orders orders) {
        System.out.println(orders.getDate() + " " + orders.getUsersId());
        this.ordersService.insertOrders(orders);
    }

    @PutMapping
    public void updateOrders(@RequestBody Orders orders) {
        this.ordersService.updateOrders(orders);
    }

    @DeleteMapping("/{ordersId}")
    public void deleteOrders(@PathVariable int ordersId) {
        this.ordersService.deleteOrders(new Orders(ordersId));
    }
}