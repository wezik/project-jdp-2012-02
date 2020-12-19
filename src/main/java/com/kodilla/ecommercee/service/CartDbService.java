package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartDbService {

    @Autowired
    CartRepository repository;

    public void createCart(Cart cart) {
        repository.save(cart);
    }

    public List<Product> getProducts(Long cartId) {
        return repository.findById(cartId).get().getProductList();
    }

}
