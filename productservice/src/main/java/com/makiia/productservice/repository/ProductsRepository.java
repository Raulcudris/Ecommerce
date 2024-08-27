package com.makiia.productservice.repository;

import com.makiia.productservice.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductsRepository extends JpaRepository<Products,Integer > {
    String FILTER_PRODUCT_QUERY = "SELECT c FROM Products c WHERE  c.id = :nroRegProduct";
    @Query(value = FILTER_PRODUCT_QUERY)
    Products findByProductId(@Param("nroRegProduct") Integer ProductId);
}
