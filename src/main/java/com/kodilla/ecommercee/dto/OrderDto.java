package com.kodilla.ecommercee.dto;


import lombok.ToString;
import lombok.Value;
import java.time.LocalDateTime;

@ToString
@Value
public class OrderDto {

    private Long id;
    private Double value;
    private String status;
    private LocalDateTime dateTime;
    private String shippingAddress;

    private UserDto userDto;
    private CartDto cartDto;




}
