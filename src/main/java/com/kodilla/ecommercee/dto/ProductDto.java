package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    Long id;
    String name;
    String description;
    BigDecimal price;
    String groupName;
    List<Cart> cartsWhichContainsThisProduct;
}
