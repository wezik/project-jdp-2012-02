package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.dto.ProductListDto;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("v1/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductDbService service;
    @Autowired
    private ProductMapper mapper;

    @GetMapping(value = "getProducts")
    public List<ProductListDto> getProducts() {
        return mapper.mapProductListToProductDtoList(service.getProducts());
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
        Product updatedProduct = service.saveProduct(mapper.mapToProduct(productDto));
        return mapper.mapToProductDto(updatedProduct);
    }

    @DeleteMapping(value = "deleteProduct/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        service.deleteProduct(productId);
    }
}
