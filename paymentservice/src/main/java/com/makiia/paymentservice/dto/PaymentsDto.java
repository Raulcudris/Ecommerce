package com.makiia.paymentservice.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaymentsDto {
    private Integer order_id;
    private long amount;
    private String payment_method;
    private String payment_status;
}
