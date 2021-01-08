package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductDbService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("v1/product")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductController {

    final ProductDbService service;
    final ProductMapper mapper;

    @GetMapping(value = "getProducts")
    public List<ProductDto> getProducts() {
        return mapper.mapToProductListDto(service.getProducts());
    }

    @GetMapping(value = "getProduct/{productId}")
    public ProductDto getProduct(@PathVariable Long productId) throws ProductNotFoundException {
        return mapper.mapToProductDto(service.getProduct(productId));
    }

    @PostMapping(value = "createProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product newProduct = service.saveProduct(mapper.mapToProduct(productDto));
        return mapper.mapToProductDto(newProduct);
    }

    @PutMapping(value = "updateProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product updatedProduct = service.updateProduct(productDto);
        return mapper.mapToProductDto(updatedProduct);
    }

    @DeleteMapping(value = "deleteProduct/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        service.deleteProduct(productId);
    }
}
