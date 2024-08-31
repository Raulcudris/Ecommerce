package com.makiia.paymentservice.controller;
import com.makiia.paymentservice.dto.PaymentsDto;
import com.makiia.paymentservice.entity.Payments;
import com.makiia.paymentservice.service.PaymentsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pay")
public class PaymentsController {
    @Autowired
    PaymentsService paymentsService;
    @GetMapping("getall")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<PaymentsDto> getAll(){
        return paymentsService.getAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<PaymentsDto> getById(@PathVariable("id") Integer id){
        Payments payments = paymentsService.getPaymentsById(id);
        if (payments == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDto(payments));
    }
    @PostMapping("create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<PaymentsDto> save(@RequestBody PaymentsDto paymentsDto){
        Payments payments = convertToEntity(paymentsDto);
        Payments savedPay = paymentsService.save(payments);
        return ResponseEntity.ok(convertToDto(savedPay));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PaymentsDto> update(@PathVariable("id") Integer id, @RequestBody PaymentsDto paymentsDto) {
        Payments product = convertToEntity(paymentsDto);
        Payments updatedOrders = paymentsService.updateOrders(id, product);
        if (updatedOrders == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDto(updatedOrders));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        boolean isDeleted = paymentsService.deleteOrder(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private PaymentsDto convertToDto(Payments payments) {
        PaymentsDto paymentsDto = new PaymentsDto();
        BeanUtils.copyProperties(payments, paymentsDto);
        return paymentsDto;
    }
    private Payments convertToEntity(PaymentsDto paymentsDto) {
        Payments payments = new Payments();
        BeanUtils.copyProperties(paymentsDto, payments);
        return payments;
    }
}
