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
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<ProductsDto> getAll(){
        return productsService.getAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ProductsDto> getById(@PathVariable("id") Integer id){
        Products product = productsService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDto(product));
    }
    @PostMapping("create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductsDto> save(@RequestBody ProductsDto productDto){
        Products product = convertToEntity(productDto);
        Products savedProduct = productsService.save(product);
        return ResponseEntity.ok(convertToDto(savedProduct));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductsDto> update(@PathVariable("id") Integer id, @RequestBody ProductsDto productDto) {
        Products product = convertToEntity(productDto);
        Products updatedProduct = productsService.updateProduct(id, product);
        if (updatedProduct == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDto(updatedProduct));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        boolean isDeleted = productsService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
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
