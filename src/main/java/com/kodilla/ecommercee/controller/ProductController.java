package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductDbService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/product")
public class ProductController {

    private final ProductDbService productDbService;
    private final ProductMapper productMapper;

    public ProductController(ProductDbService productDbService, ProductMapper productMapper) {
        this.productDbService = productDbService;
        this.productMapper = productMapper;
    }

    @GetMapping(value = "getProducts")
    public List<ProductDto> getProducts() {
        //business logic
        return null;
    }

    @GetMapping(value = "getTargetProduct")
    public ProductDto getTargetProduct(@RequestParam Long productId) throws ProductNotFoundException {
        //business logic
        return null;
    }

    @PostMapping(value = "createProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody ProductDto productDto) {
        //business logic
    }

    @PutMapping(value = "updateProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        //business logic
        return null;
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteProduct(@RequestParam Long productId) {
        //business logic
    }
}
