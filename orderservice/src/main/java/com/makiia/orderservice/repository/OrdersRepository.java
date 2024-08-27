package com.makiia.orderservice.repository;
import com.makiia.orderservice.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrdersRepository extends JpaRepository<Orders,Integer > {
    String FILTER_ORDER_QUERY = "SELECT c FROM Orders c WHERE  c.id = :nroRegOrder";
    @Query(value = FILTER_ORDER_QUERY)
    Orders findByOrderById(@Param("nroRegOrder") Integer nroRegOrder);
}
