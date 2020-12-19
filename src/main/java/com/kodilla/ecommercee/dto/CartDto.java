package com.kodilla.ecommercee.dto;

import lombok.Value;

import java.util.List;

@Value
public class CartDto {
    Long id;
    List<ProductDto> productList;
}
