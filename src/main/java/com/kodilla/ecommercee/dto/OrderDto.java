package com.kodilla.ecommercee.dto;

import lombok.Value;
import java.time.LocalDateTime;

@Value
public class OrderDto {

    private Long id;
    private Double value;
    private String status;
    private LocalDateTime dateTime;
    private String shippingAddress;

}
