package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderDbService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderDbService orderDbService;

    public OrderController(OrderMapper orderMapper, OrderDbService orderDbService) {
        this.orderMapper = orderMapper;
        this.orderDbService = orderDbService;
    }


    @GetMapping(value = "getOrder/{id}")
    public OrderDto getOrder(@PathVariable Long id ) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(
          orderDbService.getOrder(id).orElseThrow(OrderNotFoundException::new)
        );
    }

    @GetMapping(value = "getOrders")
    public List<OrderDto> getOrders(){
        List<Order> orders = orderDbService.getAllOrders();
        return orderMapper.mapToOrderDtoList(orders);
    }

    @PostMapping(value = "addOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addOrder(@RequestBody OrderDto orderDto){
        Order order = orderMapper.mapToOrder(orderDto);
        order.setDateTime(LocalDateTime.now());
        orderDbService.saveOrder(order);
    }

    @PutMapping(value = "updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto){
        Order order = orderMapper.mapToOrder(orderDto);
        Order savedOrder = orderDbService.saveOrder(order);
        return orderMapper.mapToOrderDto(savedOrder);
    }

    @DeleteMapping(value = "deleteOrder/{id}")
    public void deleteOrder(@PathVariable Long id){
        orderDbService.deleteOrderById(id);
    }

}