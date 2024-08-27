package com.makiia.orderservice.service;

import com.makiia.orderservice.entity.Orders;
import com.makiia.orderservice.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    public List<Orders> getAll(){
        return  ordersRepository.findAll();
    }

    public Orders getProductById(Integer id){
        Orders orderId = ordersRepository.findByOrderById(id);
        return  orderId;
    }

    public Orders save(Orders orders){
        Orders ordersNew = ordersRepository.save(orders);
        return ordersNew;
    }
}
