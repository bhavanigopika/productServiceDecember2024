package com.ecommerce.productservicedecember2024.repositories;

import com.ecommerce.productservicedecember2024.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //I have to registered it as bean(tell to spring as object)
public interface ProductRepository extends JpaRepository<Product, Long> {
    //what is the primary key of product is id whose data type is Long. So, apply as <Product, Long>
    //Product Repository contains all the methods(CRUD) related to the product model

    //select * from product where price > [some price which is provided by service] -> here the return type would be List<Product>
    List<Product> findProductByPriceGreaterThan(Double price);//jpa is implement this method internally and generate sql queries for this

    //select * from product where title like '%iphone' -> obviously return type is product
    List<Product> findProductByTitleLike(String title);

    //select * from product where title like 'iphone' LIMIT 5;
    //List<Product> findProductByTitleIsLike(int top, String title);

    //select * from product where price BETWEEN 10 and 25;
    List<Product> findByPriceBetween(Double lower, Double higher);

    //to avoid null pointer exception, we use optional
    Optional<Product> findById(Long productId);//findById coming from crud repository
}