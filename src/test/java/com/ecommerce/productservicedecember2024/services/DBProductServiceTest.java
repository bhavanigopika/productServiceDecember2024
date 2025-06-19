package com.ecommerce.productservicedecember2024.services;

import com.ecommerce.productservicedecember2024.exceptions.ProductNotFoundException;
import com.ecommerce.productservicedecember2024.models.Product;
import com.ecommerce.productservicedecember2024.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class DBProductServiceTest {
    //you are going to mock product repository to test this product service functionality
    //because, you are not testing the productRepository findById method.
    //You are testing getSingleProduct method in DbProductService(Why DbProductService, not FakeStoreProductService? because I am in DbProductServiceTest class not in FakeStoreProductServiceTest class)

    @Autowired//It means please use the actual bean(object)[here, dbProductService] when you use the dbProductService
    private DBProductService dbProductService;

    //Use ProductRepository as Mock dependency. This is MockitoBean
    @MockitoBean
    private ProductRepository productRepository;

    @Test
    void getSingleProduct() throws ProductNotFoundException {
        //Arrange - input, expected output, dependency
        long productId = 2L;
        Product expectedProduct = new Product();
        expectedProduct.setId(productId);
        expectedProduct.setTitle("Kia Seltos Show room");
        expectedProduct.setPrice(100000.0);

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(expectedProduct));

        //Act - actual output
        Product actualProduct = dbProductService.getSingleProduct(productId);

        //Assert - compare expected output and actual output
        assertNotNull(actualProduct);
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void getAllProductsByPages() {
    }

    @Test
    void deleteSingleProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void replaceProduct() {
    }

    @Test
    void replaceProductDto() {
    }

    @Test
    void addNewProduct() {
    }
}