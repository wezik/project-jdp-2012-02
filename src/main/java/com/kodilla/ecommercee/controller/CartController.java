package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartEntry;
import com.kodilla.ecommercee.dto.CartEntryDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.CartEntryMapper;
import com.kodilla.ecommercee.service.CartDbService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("v1/cart")
public class CartController {

    final CartMapper cartMapper;
    final CartEntryMapper cartEntryMapper;
    final CartDbService service;


    @PostMapping(value = "createCart")
    public void createCart() {
        service.createCart(cartMapper.mapToCart());
    }

    @GetMapping(value = "getProducts/{cartId}")
    public List<CartEntryDto> getProducts(@PathVariable Long cartId) {
        List<CartEntry> products = service.getProducts(cartId);
        return cartEntryMapper.mapToCartEntryDtoList(products);
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
