package com.makiia.paymentservice.service;
import com.makiia.paymentservice.entity.Payments;
import com.makiia.paymentservice.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentsService {
    @Autowired
    PaymentsRepository paymentsRepository;

    public List<Payments> getAll(){
        return  paymentsRepository.findAll();
    }

    public Payments getPaymentsById(Integer id){
        Payments payId = paymentsRepository.findByPayById(id);
        return   payId;
    }

    public Payments save(Payments payments){
        Payments payNew = paymentsRepository.save(payments);
        return payNew;
    }

    public Payments updateOrders(Integer id, Payments updatedOrder) {
        Payments existingOrder = getPaymentsById(id);
        if (existingOrder != null) {
            updatedOrder.setId(id);
            return paymentsRepository.save(updatedOrder);
        }
        return null;
    }

    public boolean deleteOrder(Integer id) {
        Payments existingOrder = getPaymentsById(id);
        if (existingOrder != null) {
            paymentsRepository.delete(existingOrder);
            return true;
        }
        return false;
    }

}
