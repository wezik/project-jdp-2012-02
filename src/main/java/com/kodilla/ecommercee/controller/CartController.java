package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.CartDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("v1/cart")
public class CartController {

    @Autowired
    private CartMapper mapper;
    @Autowired
    private CartDbService service;


    @PostMapping(value = "createCart")
    public void createCart() {
        service.createCart(mapper.mapToCart());
    }

    @GetMapping(value = "getProductList/{cartId}")
    public List<ProductDto> getProductList(@PathVariable Long cartId) {
        List<ProductDto> products = new ArrayList<>();
        return products;
    }

    @PostMapping(value = "addProduct/{productId}")
    public void addProduct(@PathVariable Long productId) {
        System.out.println("Adding product with id: " + productId);
    }

    @DeleteMapping(value = "deleteProduct/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        System.out.println("Deleting product with id: " + productId);
    }

    @PostMapping(value = "createOrder/{cartId}")
    public void createOrder(@PathVariable Long cartId) {
        System.out.println("Creating new order, based on cart with id: " + cartId);
    }

}
