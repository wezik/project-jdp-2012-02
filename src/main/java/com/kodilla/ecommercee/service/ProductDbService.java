package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.CartEntry;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProductDbService {

    final ProductRepository repository;
    final CartDbService cartDbService;

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Optional<Product> getProduct(Long productId) throws ProductNotFoundException {
        return repository.findById(productId);
    }

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public void deleteProduct(Long productId) {
        List<CartEntry> cartEntriesToDelete = getProduct(productId).get().getCartEntriesWhichContainsThisEntry();
        for(CartEntry entry : cartEntriesToDelete) {
            cartDbService.deleteProduct(entry.getId());
        }
        repository.deleteById(productId);
    }

}
