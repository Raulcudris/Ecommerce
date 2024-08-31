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

    public Orders getOrderById(Integer id){
        Orders orderId = ordersRepository.findByOrderById(id);
        return  orderId;
    }

    public Orders save(Orders orders){
        Orders ordersNew = ordersRepository.save(orders);
        return ordersNew;
    }

    public Orders updateOrders(Integer id, Orders updatedOrder) {
        Orders existingOrder = getOrderById(id);
        if (existingOrder != null) {
            updatedOrder.setId(id);
            return ordersRepository.save(updatedOrder);
        }
        return null;
    }

    public boolean deleteOrder(Integer id) {
        Orders existingOrder = getOrderById(id);
        if (existingOrder != null) {
            ordersRepository.delete(existingOrder);
            return true;
        }
        return false;
    }

}
