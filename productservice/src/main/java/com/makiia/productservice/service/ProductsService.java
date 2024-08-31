package com.makiia.productservice.service;
import com.makiia.productservice.entity.Products;
import com.makiia.productservice.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductsService {

    @Autowired
    ProductsRepository productRepository;

    public List<Products> getAll(){
        return  productRepository.findAll();
    }

    public Products getProductById(Integer id){
        Products productId = productRepository.findByProductId(id);
        return  productId;
    }

    public Products save(Products product){
        Products productNew = productRepository.save(product);
        return productNew;
    }

    public Products updateProduct(Integer id, Products updatedProduct) {
        Products existingProduct = getProductById(id);
        if (existingProduct != null) {
            updatedProduct.setId(id);
            return productRepository.save(updatedProduct);
        }
        return null;
    }

    public boolean deleteProduct(Integer id) {
        Products existingProduct = getProductById(id);
        if (existingProduct != null) {
            productRepository.delete(existingProduct);
            return true;
        }
        return false;
    }

}
