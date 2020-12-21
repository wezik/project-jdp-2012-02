package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.OrderDto;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @GetMapping(value = "getOrder/{id}")
    public OrderDto getOrder(@PathVariable Long id ) {
        return new OrderDto(1L,10.99);
    }

    @GetMapping(value = "getOrders")
    public List<OrderDto> getOrders(){
        List<OrderDto> orders = new ArrayList<>();
        orders.add(new OrderDto(1L,111.99));
        orders.add(new OrderDto(2L, 49.99));
        return orders;
    }

    @PostMapping(value = "addOrder" )
    public OrderDto addOrder(@RequestBody OrderDto orderDto){
        return orderDto;
    }

    @PutMapping(value = "updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto){
        return orderDto;
    }

    @DeleteMapping(value = "deleteOrder/{id}")
    public boolean deleteOrder(@PathVariable Long id){
        return false;
    }

}