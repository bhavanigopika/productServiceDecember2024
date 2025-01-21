package com.ecommerce.productservicedecember2024.repositories;

import com.ecommerce.productservicedecember2024.models.Product;
import com.ecommerce.productservicedecember2024.projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //I have to registered it as bean(tell to spring as object)
public interface ProductRepository extends JpaRepository<Product, Long> {
    //what is the primary key of product is id whose data type is Long. So, apply as <Product, Long>
    //Product Repository contains all the methods(CRUD) related to the product model

    /*Declared Queries*/

    //select * from product where price > [some price which is provided by service] -> here the return type would be List<Product>
    List<Product> findProductByPriceGreaterThan(Double price);//jpa is implement this method internally and generate sql queries for this

    //select * from product where title like '%iphone' -> obviously return type is product
    List<Product> findProductByTitleLike(String title);

    //select * from product where title like 'iphone' LIMIT 5;
    //List<Product> findProductByTitleIsLike(int top, String title);

    //select * from product where price BETWEEN 10 and 25;
    List<Product> findByPriceBetween(Double lower, Double higher);

    //The below things are extended from crud repository. So no need to write findById, findAll,

    /*
    //to avoid null pointer exception, we use optional
    Optional<Product> findById(Long productId);//findById coming from crud repository, so we can directly apply in Services

    //get all products
    List<Product> findAll();

    //delete the single product by id
    void deleteById(Long aLong);
    */

    /*Hibernate Query Language - HQL*/

    /*
    * Using declared queries, we are getting a list of product or single product(whole attributes included)
    * But we want only id and title from product, then we can use HQL
    * Example:
    *     Fetch 1 million(10^6) products id and title. Note: 10^6 = 1 MB, 10^9 = 1 GB
    *     select id, title from product where createdAt > timestamp;
    *     Say, product model has 100 attributes
    *
    *     When we fetch a product, we store it in memory(RAM)
    *     Each product(product_id, created_At, updated_At, title, price, category(name, description),.....) = (say)100 bytes
    *     If 1 mn products, then 100 * 10^6 = 100 MB of RAM to fetch all product attributes
    *
    *     But I want to get id and title: id(long) = 8 bytes, title(String) = (say, based on character)22 bytes => 8 + 22 = 30 bytes
    *     If 1 mn products, then 30 * 10^6 = 30 MB of RAM to fetch id and title
    *
    *     So, if we fetch entire 100 product = 100 MB, but we want only id and title, we need only 30 MB. For this case, we can go for HQL instead of declared queries to reduce the memory usage.
    *     Since declared queries helped to take list of product or single product(all attributes) not provide specific attributes of product
    *  */

    //To define hql, then use query parameter. HQL - helpful to define more custom queries
    //refer Hibernate Query Language.docx
    //Here, Product is model not table, table we referred as product - Note down in HQL
    @Query("select p.id as id, p.title as title from Product p")
    List<ProductWithIdAndTitle> randomSearchMethodForProduct();

    /* Native Queries : Our case in native queries is SQL (i.e) we can write sql queries here as well*/

    //Here, we can write "product" referred as table not as captial 'P' Product. Just have to write like MySQL query. If we write in Oracle query,
    //then we have to write like it as Oracle Query here - Note down in Native Query
    @Query(nativeQuery = true, value = "select p.id as id, p.title as title from product p")
    List<ProductWithIdAndTitle> nativeSearchMethodForProduct();
}