package com.kodilla.ecommercee.dto;

import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddCartEntryDto {
    Long cartId;
    Long productId;
    Long quantity;
}
