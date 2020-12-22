package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartProduct;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CartDbService {

    final CartRepository repository;

    public void createCart(Cart cart) {
        repository.save(cart);
    }

    public List<CartProduct> getProducts(Long cartId) {
        return repository.findById(cartId).get().getProductList();
    }

}
