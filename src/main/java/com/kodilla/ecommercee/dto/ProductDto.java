package com.kodilla.ecommercee.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;

@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    Long id;
    String name;
    String description;
    BigDecimal price;
    Long groupId;
}
