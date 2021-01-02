package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.Cart;
import lombok.Value;
import java.time.LocalDateTime;

@Value
public class OrderDto {

    private Long id;
    private Double value;
    private String status;
    private LocalDateTime dateTime;
    private String shippingAddress;
    private Cart cart;

}
