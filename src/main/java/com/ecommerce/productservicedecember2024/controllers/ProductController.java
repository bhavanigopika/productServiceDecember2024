package com.ecommerce.productservicedecember2024.controllers;

import com.ecommerce.productservicedecember2024.models.Category;
import com.ecommerce.productservicedecember2024.models.Product;
import com.ecommerce.productservicedecember2024.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController/*spring says, I will create object of product controller, to say product service as an object to spring, mention @Service in fakeStore product service */
@RequestMapping("/product")
public class ProductController {

    ProductService productService;//instance(object created from a class) of the product service interface

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){/*@Qualifier - we can choose among which bean object is to use either DBProductService or FakeStoreProductService*/
                                                                                                  //here, this productService coming from spring. spring puts the service bean to here. spring framework created as bean and store them in IOC container
        this.productService = productService;
    }
   /* @GetMapping(
            path="/{id}", produces = "application/json"
    )*/
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        //return productService.getSingleProduct(id);//return type is product here, I have mentioned directly here
        Product product = productService.getSingleProduct(id);
        return product;

       /* Product product = new Product("abc",2.5D, new Category("cat1","cat1 desc"));
        Product product1 = new Product();
        return product1;*/
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();//this will return list of productsList<Products>
    }

    //@RequestBody - converts received json to Product java object and here it returns a product
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.replaceProduct(id, product);
    }

    //partial update
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

}
/*
Product Service
1. create product
2. get product
3. update product
4. delete product

MySQL DB we have to use here

Client talk to -----> Product Service interact with  -----> Database(Product table: id, quantity, name, price)

We have to use FakeStore API to code our Product Service
  1. Why we use FakeStore API? Based on this, we are trying to understand the third party API. So, product service use third party API. Right now, not use database

Here, now

Client talk to -----> Product Service interact with  -----> FakeStore API (Product table: id, quantity, name, price)

FakeStore API gives product data. So, product service is proxy service and actual service is FakeStore API.
Later, we replace the fakestore API into Database. Simply, we can say, get the ready-made product data from FakeStore API instead of creating the database right now.

Learning today - Agenda: How we are going to call third party APIs from our product Service


 */