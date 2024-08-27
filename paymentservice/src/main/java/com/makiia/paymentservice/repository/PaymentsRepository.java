package com.makiia.paymentservice.repository;
import com.makiia.paymentservice.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentsRepository extends JpaRepository<Payments,Integer > {
    String FILTER_PAY_QUERY = "SELECT c FROM Payments c WHERE  c.id = :nroRegPay";
    @Query(value = FILTER_PAY_QUERY)
    Payments findByPayById(@Param("nroRegPay") Integer nroRegPay);
}
