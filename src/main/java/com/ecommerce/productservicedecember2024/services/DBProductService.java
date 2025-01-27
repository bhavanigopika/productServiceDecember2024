package com.ecommerce.productservicedecember2024.services;

import com.ecommerce.productservicedecember2024.dto.GetProductDto;
import com.ecommerce.productservicedecember2024.exceptions.ProductNotFoundException;
import com.ecommerce.productservicedecember2024.models.Category;
import com.ecommerce.productservicedecember2024.models.Product;
import com.ecommerce.productservicedecember2024.repositories.CategoryRepository;
import com.ecommerce.productservicedecember2024.repositories.ProductRepository;
import com.fasterxml.jackson.core.ObjectCodec;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

//@Service use for service class, @Repository use for repository class, @Component use for object creation. So, all are coming form stereotype, see 4th, 5th, 6th line
@Service("dbProductService")
public class DBProductService implements ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public DBProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        //To avoid null pointer exception, we are using optional...since our productId or title or something which we search might be null

        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("The product with id " + productId + " does not exist");
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {

        //Why here we didn't use optional? because list of array is not going to be null, list should be empty...so, not possible of null pointer exception
        List<Product> products = productRepository.findAll();

        return products;
    }

    @Override
    public void deleteSingleProduct(Long productId) throws ProductNotFoundException {

        //The following line execute and delete it but not return.
        //productRepository.deleteById(product_id);

        //Let's delete the product
        //check if the product is present or not
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("The product with id " + productId + " does not exist");
        }
        productRepository.deleteById(productId);
    }

    //update is PATCH operation
    @Override
    public Product updateProduct(Long productId, Product product) throws ProductNotFoundException {
        //first get the product
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("The product with id " + productId + " does not exist");
        }

        //get all the product details from optionalProduct bucket
        Product productInDb = optionalProduct.get();

        if(product.getTitle() != null){
            productInDb.setTitle(product.getTitle());
        }
        if(product.getPrice() != null){
            productInDb.setPrice(product.getPrice());
        }
        return productRepository.save(productInDb);
    }

    //TODO: replace the product - In this put mapping, when I replace one of the product, primary key (id) should be same. But I coded as a new id for product
    //replace is PUT operation
    @Override
    public Product replaceProduct(Long productId, Product newProduct) throws ProductNotFoundException {


        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("The product with id " + productId + " does not exist");
        }

        Product p1 = new Product();
        p1.setTitle(newProduct.getTitle());
        p1.setPrice(newProduct.getPrice());
        return productRepository.save(p1);


//        return productRepository.findById(productId)
//            .map(p -> {
//                p.setTitle(newProduct.getTitle());
//                return productRepository.save(p);
//                })
//                .orElseGet(() -> {
//                    return productRepository.save(newProduct);
//                });
    }

    @Override
    public GetProductDto replaceProductDto(Long productId, Product product) throws ProductNotFoundException {
        //first get the product

/*        GetProductDto getProductDto = new GetProductDto();
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("The product with id " + productId + " does not exist");
        }

        Product productInDb = optionalProduct.get();

        if(productInDb.getTitle() != null){
            productInDb.setTitle(product.getTitle());
        }
        if(optionalProduct.get().getPrice() != null){
            productInDb.setPrice(product.getPrice());
        }
        return productRepository.save(productInDb);*/
        return null;
    }

    @Override
    public Product addNewProduct(Product product) {
        /*
        In product table we have id, created_at, updated_at, price, title, category_id. We do @ManytoOne mapping here.
        So get category_id in product table

        In category table we have id, created_at, updated_at, description, name.
        Based on this scenario, I first get the category from db, then using category get the product.
        create the category repository and save the category details first, then get the category to retrieve the product details

        Final goal is to save the category first which is inner object of product, then save the product
        */

         /*
         Suppose, If we run with this "return productRepository.save(product); " line, we get this message in post man

         "message": "org.hibernate.TransientObjectException: persistent instance references an unsaved transient
         instance of 'com.ecommerce.productservicedecember2024.models.Category' (save the transient instance
         before flushing)"
         */
        //return productRepository.save(product);

        //Step1: First get the category object
        Category category = product.getCategory();
        //Step2: check if the category is created or not...if category not created in table then that is null
        /*if(category.getId() == null){
        *//*  Step3: We need to create the new category object in DB first if the category is null - so, create CategoryRepository
            Step4: Next, save the category in category repository
            Step5: Here, save do 2 operation - insert, update. id is not present for category, so it is insert operation, if id is already present
                   then it is update operation. Here we've insert operation
        *//*
            category = categoryRepository.save(category);
            //once saved, then set the category to the product...
            product.setCategory(category);
        }*/

        //Better way to check if the category id is created or not is by - Cascade(When we perform some action on the target entity,
        //                                                                         the same action will be applied to the associated entity.)
        //So, comment it from 161 to 170
        //now, save it to the product
        return productRepository.save(product);

        /*

        This is what we give as product json
        {
        "title": "One Plus ",
        "price": 999,
        "category": {
        "name": "electronics",
        "description": "one plus 13R"
        }
        }

        Result of this post mapping is:
        {
        "id": 12,
        "createdAt": null,
        "updatedAt": null,
        "title": "One Plus ",
        "price": 999.0,
        "category": {
        "id": 5,
        "createdAt": null,
        "updatedAt": null,
        "name": "electronics",
        "description": "one plus 13R"
        }
        }
        */
    }

}
