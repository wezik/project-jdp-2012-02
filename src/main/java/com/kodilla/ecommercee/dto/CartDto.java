package com.kodilla.ecommercee.dto;

import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Value
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartDto {
    Long id;
    Long orderId;
    List<CartEntryDto> cartEntryList;
}
