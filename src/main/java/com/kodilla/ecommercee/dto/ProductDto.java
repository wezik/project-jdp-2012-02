package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.Cart;
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
    Long groupId;
    List<Cart> cartsWhichContainsThisProduct;
}
