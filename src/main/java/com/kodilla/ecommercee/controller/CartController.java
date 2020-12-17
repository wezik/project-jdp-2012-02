package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("v1/cart")
public class CartController {

    @PostMapping(value = "createCart")
    public void createCart() {
        System.out.println("Creating empty cart");
    }

    @GetMapping(value = "getProductList/{cartId}")
    public List<ProductDto> getProductList(@PathVariable Long cartId) {
        List<ProductDto> products = new ArrayList<>();
        products.add(new ProductDto(cartId, "test product"));
        return products;
    }

    @PostMapping(value = "addProduct")
    public void addProduct(@RequestParam Long productId) {
        System.out.println("Adding product with id: " + productId);
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteProduct(@RequestParam Long productId) {
        System.out.println("Deleting product with id: " + productId);
    }

    @PostMapping(value = "createOrder")
    public void createOrder(@RequestParam Long cartId) {
        System.out.println("Creating new order, based on cart with id: " + cartId);
    }

}
