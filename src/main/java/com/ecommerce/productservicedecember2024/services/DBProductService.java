package com.ecommerce.productservicedecember2024.services;

import com.ecommerce.productservicedecember2024.exceptions.ProductNotFoundException;
import com.ecommerce.productservicedecember2024.models.Product;
import com.ecommerce.productservicedecember2024.repositories.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service use for service class, @Repository use for repository class, @Component use for object creation. So, all are coming form stereotype, see 4th, 5th, 6th line
@Service("dbProductService")
public class DBProductService implements ProductService {

    ProductRepository productRepository;

    DBProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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

        //get the product from optionalProduct bucket
        Product productInDb = optionalProduct.get();

        if(product.getTitle() != null){
            productInDb.setTitle(product.getTitle());
        }
        if(product.getPrice() != null){
            productInDb.setPrice(product.getPrice());
        }
        return productRepository.save(productInDb);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }
}
