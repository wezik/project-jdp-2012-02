package com.kodilla.ecommercee.dto;

import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductListDto {
    Long id;
    String name;
    String description;
    BigDecimal price;
    Long groupId;
}
