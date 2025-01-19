package com.ecommerce.productservicedecember2024.controllers;

import com.ecommerce.productservicedecember2024.dto.GetProductDto;
import com.ecommerce.productservicedecember2024.exceptions.ProductNotFoundException;
import com.ecommerce.productservicedecember2024.models.Category;
import com.ecommerce.productservicedecember2024.models.Product;
import com.ecommerce.productservicedecember2024.repositories.ProductRepository;
import com.ecommerce.productservicedecember2024.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController/*spring says, I will create object of product controller, to say product service as an object to spring, mention @Service in fakeStore product service */
@RequestMapping("/product")
//@Configuration
public class ProductController {
    //@Qualifier("${my.bean.qualifier}")
    ProductService productService;//instance(object created from a class) of the product service interface

    public ProductController(@Qualifier("dbProductService") ProductService productService){//@Qualifier - we can choose among which bean object is to use either DBProductService or FakeStoreProductService
                                                                                                  //here, this productService coming from spring. spring puts the service bean to here. spring framework created as bean and store them in IOC container
        this.productService = productService;
    }

    /*public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }*/

    //Another way to provide @Qualifier is application.properties(check in the configuration file) - so, provide variable name and use configuration file to add the qualifier
    @GetMapping(
            path="/{id}", produces = "application/json"
    )
    public Product getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        //return productService.getSingleProduct(id);//return type is product here, I have mentioned directly here
        Product product = productService.getSingleProduct(id);
        return product;

       /* Product product = new Product("abc",2.5D, new Category("cat1","cat1 desc"));
        Product product1 = new Product();
        return product1;*/
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Product> getProductByIdAndCheckResponseEntity(@PathVariable("id") Long id) throws ProductNotFoundException {

        //return the product from the productService here. If we want to additionally return say, status code, then use responseEntity, the responseEntity type is Product(i.e) sending back to the product
        /*
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.OK);
        return responseEntity;
        */

        //throw new RuntimeException("This is an Exception. This is thrown from Product Controller");

        //We don't want to get error in product controller, so error should come from product service
        //also, error should not appear like the stack trace.Hence, do it gracefully by performing the code in try-catch block
       /* ResponseEntity<Product> response = null;
        try{
            Product product = productService.getSingleProduct(id);
            response = new ResponseEntity<>(product, HttpStatus.OK);
        }catch(RuntimeException e){
            response = new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }

        return response;*/

        //Controller - should act as waiter. So, handle the exception in controller is also not better. So, handle it in GlobalExceptionHandler whatever Service layer returns to this Controller. Controller goes
        // to controllerAdivce and exception message goes to the client. Why we are doing like this because we are using java in spring framework not simply doing the code as java

        ResponseEntity<Product> responseEntity = new ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.OK);
        return responseEntity;
    }

    /*****/

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();//this will return list of productsList<Products>
    }

    //Get the status for get all products also
    @GetMapping("/status")
    public ResponseEntity<List<Product>> getAllProductsAndCheckResponseEntity(){
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        return responseEntity;
    }

    /*****/

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long product_id) throws ProductNotFoundException {
        productService.deleteSingleProduct(product_id);
    }

    /*****/

    //partial update (i.e) specific value update - patch mapping
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product newProduct) throws ProductNotFoundException {
        return productService.updateProduct(id, newProduct);
    }

    /*****/

    //update everything = put mapping
    //@RequestBody - converts received json to Product java object and here it returns a product
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.replaceProduct(id, product);
    }

    @PutMapping("/put/id")
    public GetProductDto replaceProductDto(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.replaceProductDto(id, product);
    }

    /*****/

    //create the product - post mapping
    @PostMapping()
    public Product addNewProduct(@RequestBody Product product){
        return productService.addNewProduct(product);
    }

    /*****/


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