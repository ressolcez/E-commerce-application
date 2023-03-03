package pl.britenet.campusspringjune.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.consoleapp.obj.model.Cart;
import pl.britenet.consoleapp.service.CartService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public Optional<Collection<Cart>> getAllCart() {
        return this.cartService.getAllCart();
    }

    @GetMapping("/{cartId}")
    public Optional<Cart> getCart(@PathVariable int cartId) {
        return this.cartService.getCart(cartId);
    }

    @GetMapping("/getCart/{usersId}")
    public Optional<Cart> getCartUser(@PathVariable int usersId) {
        return this.cartService.getCartUser(usersId);
    }

    @PostMapping
    public void insertCart(@RequestBody Cart cart) {
        this.cartService.insertCart(cart);
    }

    @PutMapping
    public void updateCart(@RequestBody Cart cart) {
        this.cartService.updateCart(cart);
    }

    @DeleteMapping("/{cartId}")
    public void deleteCart(@PathVariable int cartId) {
        this.cartService.deleteCart(new Cart(cartId));
    }
}
