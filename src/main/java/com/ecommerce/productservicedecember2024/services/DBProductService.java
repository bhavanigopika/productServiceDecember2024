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
        return List.of();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }
}
