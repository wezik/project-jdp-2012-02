package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/v1/order")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderDbService orderDbService;

    public OrderController() {
    }


    @GetMapping(value = "getOrder/{id}")
    public OrderDto getOrder(@PathVariable Long id ) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(
          orderDbService.getOrder(id).orElseThrow(OrderNotFoundException::new)
        );
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