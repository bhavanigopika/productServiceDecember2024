package com.ecommerce.productservicedecember2024.services;

import com.ecommerce.productservicedecember2024.models.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service use for service class, @Repository use for repository class, @Component use for object creation. So, all are coming form stereotype, see 4th, 5th, 6th line
@Service
public class DBProductService implements ProductService {

    @Override
    public Product getSingleProduct(Long productId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
