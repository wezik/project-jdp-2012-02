package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CreateProductDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    @GetMapping(value = "getProducts")
    public List<ProductDto> getProducts() {
        List<ProductDto> products = new ArrayList<>();
        products.add(new ProductDto(1L,"Test product"));
        return products;
    }

    @GetMapping(value = "getProduct")
    public ProductDto getProduct(@RequestParam Long productId) throws ProductNotFoundException {
        System.out.println("Looking for product with id: " + productId);
        return new ProductDto(1L, "Test product");
    }

    @PostMapping(value = "createProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody CreateProductDto createProduct) {
        System.out.println("Creating product: " + createProduct);
    }

    @PutMapping(value = "updateProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        System.out.println("Updating product: " + productDto);
        return new ProductDto(1L, "Updated test product");
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteProduct(@RequestParam Long productId) {
        System.out.println("Deleting product with id: " + productId);
    }
}
