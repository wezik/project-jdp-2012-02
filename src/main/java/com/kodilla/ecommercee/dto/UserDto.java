package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.Order;
import lombok.Value;

import java.util.List;

@Value
public class UserDto {

    private Long id;
    private String username;
    private String status;
    private Long userKey;
    private List<Order> orderList;
}
