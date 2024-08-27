package com.makiia.orderservice.controller;

import com.makiia.orderservice.dto.OrdersDto;
import com.makiia.orderservice.entity.Orders;
import com.makiia.orderservice.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrdersService ordersService;

    @GetMapping("getall")
    public List<OrdersDto> getAll(){
        return ordersService.getAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrdersDto> getById(@PathVariable("id") Integer id){
        Orders product = ordersService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDto(product));
    }
    @PostMapping("create")
    public ResponseEntity<OrdersDto> save(@RequestBody OrdersDto ordersDto){
        Orders order = convertToEntity(ordersDto);
        Orders savedOrder = ordersService.save(order);
        return ResponseEntity.ok(convertToDto(savedOrder));
    }

    private OrdersDto convertToDto(Orders orders) {
        OrdersDto ordersDto = new OrdersDto();
        BeanUtils.copyProperties(orders, ordersDto);
        return ordersDto;
    }
    private Orders convertToEntity(OrdersDto ordersDto) {
        Orders orders = new Orders();
        BeanUtils.copyProperties(ordersDto, orders);
        return orders;
    }
}
