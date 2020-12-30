package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.OrderDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    public OrderDto mapToOrderDto(final Order order){
        return new OrderDto(
                order.getId(),
                order.getValue(),
                order.getStatus(),
                order.getDateTime(),
                order.getShippingAddress(),
                order.getUser(),
                order.getCart()
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> ordersList) {
        return ordersList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }

    public Order mapToOrder(final OrderDto orderDto){
        return new Order(
                orderDto.getId(),
                orderDto.getValue(),
                orderDto.getStatus(),
                orderDto.getDateTime(),
                orderDto.getShippingAddress(),
                orderDto.getUser(),
                orderDto.getCart()
        );
    }
}
