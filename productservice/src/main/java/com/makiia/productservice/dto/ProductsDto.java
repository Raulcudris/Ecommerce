package com.makiia.productservice.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductsDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Integer category_id;
}
