package pl.britenet.campusspringjune.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.consoleapp.obj.model.CartProduct;
import pl.britenet.consoleapp.service.CartProductService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cartProduct")
@CrossOrigin("*")
public class CartProductController {
    private final CartProductService cartProductService;

    @Autowired
    public CartProductController(CartProductService cartProductService) {
        this.cartProductService = cartProductService;
    }

    @GetMapping
    public Optional<Collection<CartProduct>> getAllCartProduct() {
        return this.cartProductService.getAllCartProduct();
    }

    @GetMapping("/{cartProductId}")
    public Optional<CartProduct> getCartProduct(@PathVariable int cartProductId) {
        return this.cartProductService.getCartProduct(cartProductId);
    }

    @PostMapping("/{sign}")
    public void insertCartProduct(@RequestBody CartProduct cartProduct,@PathVariable String sign) {
        this.cartProductService.insertCartProduct(cartProduct,sign);
    }

    @PutMapping
    public void updateCartProduct(@RequestBody CartProduct cartProduct) {
        this.cartProductService.updateCartProduct(cartProduct);
    }

    @DeleteMapping("/{cartProductId}")
    public void deleteCartProduct(@PathVariable int cartProductId) {
        this.cartProductService.deleteCartProduct(new CartProduct(cartProductId));
    }
}