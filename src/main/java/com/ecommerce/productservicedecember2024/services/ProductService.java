package com.ecommerce.productservicedecember2024.services;

import com.ecommerce.productservicedecember2024.models.Product;

import java.util.List;

public interface ProductService {
    //This is interface, so all messages going to be public and static
    //Any class who is going to implement the product service should have the same method signature

    //So, every concrete implementation of product service is going to follow the same method signature
    Product getSingleProduct(Long productId);//return type would be Product
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product product);
    Product replaceProduct(Long id, Product product);
}
