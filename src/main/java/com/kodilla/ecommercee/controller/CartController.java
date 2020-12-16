package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.service.CartDbService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/cart")
public class CartController {

    private final CartRepository cartRepository;
    private final CartDbService cartDbService;

    public CartController(CartRepository cartRepository, CartDbService cartDbService) {
        this.cartRepository = cartRepository;
        this.cartDbService = cartDbService;
    }

    @PostMapping(value = "createEmptyBasket", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createEmptyBasket(@RequestBody CartDto cartDto) {
        //business logic
    }

    @GetMapping(value = "getProducts")
    public List<ProductDto> getProductsFromBasket(@RequestParam Long cartId) {
        //business logic
        return null;
    }

    @PostMapping(value = "addProduct")
    public void addProductToBasket(@RequestParam Long productId, @RequestParam Long cartId) {
        //business logic
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteProductFromBasket(@RequestParam Long productId, @RequestParam Long cartId) {
        //business logic
    }

    @PostMapping(value = "createOrder")
    public void createOrder(@RequestParam Long cartId) {
        //business logic
    }
}
