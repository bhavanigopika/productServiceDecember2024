package com.ecommerce.productservicedecember2024.controllers;

import com.ecommerce.productservicedecember2024.dto.GetProductDto;
import com.ecommerce.productservicedecember2024.exceptions.ProductNotFoundException;
import com.ecommerce.productservicedecember2024.models.Category;
import com.ecommerce.productservicedecember2024.models.Product;
import com.ecommerce.productservicedecember2024.repositories.ProductRepository;
import com.ecommerce.productservicedecember2024.services.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController/*spring says, I will create object of product controller, to say product service as an object to spring, mention @Service in fakeStore product service */
@RequestMapping("/product")
//@Configuration
public class ProductController {
    //@Qualifier("${my.bean.qualifier}")
    ProductService productService;//instance(object created from a class) of the product service interface

    //we can use @Primary in one of the service either dbProductService or fakeStoreProductService specifically to that service whichever we want
    //@Primary -> indicates a default bean to be injected when no specific qualifer is provided
    //@Qualifier -> specifies exactly when bean should be injected when multiple candidated exist.
    //So, use @Qualifier which has higher priority than @Primary means it will override the @Primary selection

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
    //public ProductController(@Qualifier("dbProductService") ProductService productService){//@Qualifier - we can choose among which bean object is to use either DBProductService or FakeStoreProductService
                                                                                                  //here, this productService coming from spring. spring puts the service bean to here. spring framework created as bean and store them in IOC container
    //public ProductController(ProductService productService){
        this.productService = productService;
    }

    /*public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }*/

    //Another way to provide @Qualifier is application.properties(check in the configuration file) - so, provide variable name and use configuration file to add the qualifier
    @GetMapping(path="/{id}", produces = "application/json")
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
        // to controllerAdvice and exception message goes to the client. Why we are doing like this because we are using java in spring framework not simply doing the code as java

        ResponseEntity<Product> responseEntity = new ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.OK);
        return responseEntity;

        //To learn test case for negative scenario, change the code
        //call the productService, but you return a new Product, see line no:101

        //Product product = productService.getSingleProduct(id);

        //Let's add the product

        //Scenario 1: add the product with some values
//        Product p1 = new Product();
//        p1.setId(id);
//        p1.setPrice(480.0);
//        p1.setTitle("Books");
//        ResponseEntity<Product> responseEntity = new ResponseEntity<>(p1, HttpStatus.OK);
//        return responseEntity;

        //Scenario 2: add the empty product as new Product
//        ResponseEntity<Product> responseEntity1 = new ResponseEntity<>(new Product()/*product*/, HttpStatus.OK);
//        return responseEntity1;
    }

    /*****/

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();//this will return list of productsList<Products>
    }

    //Get the status for get all products also
    @GetMapping("/status")
    public ResponseEntity<List<Product>> getAllProductsAndCheckResponseEntity(){
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping()
    public ResponseEntity<Page<Product>> getAllProductsByPages(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize){
        ResponseEntity<Page<Product>>  responseEntity1 = new ResponseEntity<>(productService.getAllProductsByPages(pageNumber, pageSize), HttpStatus.OK);
        return responseEntity1;
    }

    /*****/

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long product_id) throws ProductNotFoundException {
        productService.deleteSingleProduct(product_id);
    }

    /*****/

    //partial update (i.e) specific value update (i.e) update whatever is provided - patch mapping
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product newProduct) throws ProductNotFoundException {
        return productService.updateProduct(id, newProduct);
    }

    /*****/

    //replace the row/object = put mapping
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

/*
PUT - replace the row/object
product -> name(NOT NULL), description(NULLABLE), price(NOT NULL), category(NULLABLE)

PUT -> Product -> If we give => name, description, price, category = no problem here
    -> Product -> If we give => name, null, price, category - here, problem on creating the product because we set description as not null in Category model.
                                 => If we want to create the product, then set @Nullable on description in model Category
                                 => Eventhough here the product database is nullable, but our use cse mentioned description as not nullable
    -> Product -> If we give => null, description, price, category = here problem on creating the product because here the product database mentioned as nullable.

PATCH - update whatever is provided(partial update)

@Autowired - The purpose of autowired, automatically resolves and inject the bean or dependency into marked field constructor or setter method.

class A{
 B b;
}

A a = new A();
So, here B is dependency, A is dependent on B.
A is product controller, B is product Service here. So, to avoid tight coupling, I've used product service in interface

@Annotation that tells that please create an aobject/bean and store it in AppContext or spring context or IOC Container
class B{

}

@Service, @repository, @Bean, @Configuration, @RestController, @ResMapping - telling to spring that it is a bean (i.e) object, please
put it into the application context. Whenever, the application needs this type of object, they can use it from application context. So,
pass the dependency from outside to avoid tight coupling. Spring provides this features for us...

Dependency Injection:

Dependency - an object that another object relies on to perform its work
B is the dependency A relies on but A wants to perform some task

Injection - the process of passing the dependencies into dependent object. How? Spring does this...

How DI happens? DI happens using 1) Constructor DI 2) Setter DI 3) Field injection DI
 */

/*
Pagination:

Pageable = represents the request for a specific page of data
PageRequest = concrete implementation of Pageable(Pageable is interface)
              PageRequest helps us with pagination which controls page number, page size
              Why does it help? It reduces the db load, fetching the necessary/required records
              PageRequest is powerful and efficient way to handle pagination and sorting also in spring data jpa.
 */