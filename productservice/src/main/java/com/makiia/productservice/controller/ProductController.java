package com.makiia.productservice.controller;
import com.makiia.productservice.dto.ProductsDto;
import com.makiia.productservice.entity.Products;
import com.makiia.productservice.service.ProductsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductsService productsService;

    @GetMapping("getall")
    @PreAuthorize("hasRole('admin')")
    public List<ProductsDto> getAll(){
        return productsService.getAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ProductsDto> getById(@PathVariable("id") Integer id){
        Products product = productsService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDto(product));
    }
    @PostMapping("create")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ProductsDto> save(@RequestBody ProductsDto productDto){
        Products product = convertToEntity(productDto);
        Products savedProduct = productsService.save(product);
        return ResponseEntity.ok(convertToDto(savedProduct));
    }

    private ProductsDto convertToDto(Products product) {
        ProductsDto productDto = new ProductsDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }
    private Products convertToEntity(ProductsDto productDto) {
        Products product = new Products();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
