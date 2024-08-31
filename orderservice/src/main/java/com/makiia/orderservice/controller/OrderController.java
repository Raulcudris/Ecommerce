package com.makiia.orderservice.controller;

import com.makiia.orderservice.dto.OrdersDto;
import com.makiia.orderservice.entity.Orders;
import com.makiia.orderservice.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrdersService ordersService;

    @GetMapping("getall")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<OrdersDto> getAll(){
        return ordersService.getAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<OrdersDto> getById(@PathVariable("id") Integer id){
        Orders order = ordersService.getOrderById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDto(order));
    }
    @PostMapping("create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<OrdersDto> save(@RequestBody OrdersDto ordersDto){
        Orders order = convertToEntity(ordersDto);
        Orders savedOrder = ordersService.save(order);
        return ResponseEntity.ok(convertToDto(savedOrder));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrdersDto> update(@PathVariable("id") Integer id, @RequestBody OrdersDto ordersDto) {
        Orders product = convertToEntity(ordersDto);
        Orders updatedOrders = ordersService.updateOrders(id, product);
        if (updatedOrders == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDto(updatedOrders));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        boolean isDeleted = ordersService.deleteOrder(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
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
