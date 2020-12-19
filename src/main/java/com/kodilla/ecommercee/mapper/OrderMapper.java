package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.OrderDto;

public class OrderMapper {

    public OrderDto mapToOrderDto(final Order order){
        return new OrderDto();
    }
}
