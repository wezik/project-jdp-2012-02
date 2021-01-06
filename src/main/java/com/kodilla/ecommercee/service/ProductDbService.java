package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.CartEntry;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProductDbService {

    final ProductRepository repository;
    final GroupDbService groupDbService;
    final CartEntryDbService cartEntryDbService;

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProduct(Long productId) throws ProductNotFoundException {
        return repository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(ProductDto productDto) {
        Group group = groupDbService.getGroup(productDto.getGroupId()).orElseThrow(GroupNotFoundException::new);
        Product product = getProduct(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setGroup(group);
        saveProduct(product);
        return product;
    }

    public void deleteProduct(Long productId) {
        Product extracted = getProduct(productId);
        List<CartEntry> entries = new ArrayList<>(extracted.getCartEntriesWhichContainsThisEntry());
        for (CartEntry iterationEntry : entries) {
            iterationEntry.removeRelationsFromCartAndProductTables();
            cartEntryDbService.saveEntry(iterationEntry);
            cartEntryDbService.deleteEntry(iterationEntry.getId());
        }
        repository.deleteById(productId);
    }

}
