package com.ecommerce.productservicedecember2024.controllers;

import com.ecommerce.productservicedecember2024.exceptions.ProductNotFoundException;
import com.ecommerce.productservicedecember2024.models.Product;
import com.ecommerce.productservicedecember2024.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    //Show we mock the product controller? No, because we want to test the real product controller. Inside productController.java, we wrote productService which we called.
    //Then do we have to mock productService? YES, no need to test the functionality of productService. So, we mock this productService. Hence, we hardcoded the value of productService and shows productService works fine because we say it is isolation(unit testing)

    @Autowired //This shows error earlier because bean(object) of productController is already created when the application starts, use that product controller bean but when I test, does spring boot project has started? No. So, use @SpringBootTest annotation. Now, error has gone.
               //once mention @SpringBootTest, then application context loaded then take the bean of ProductController in application context. It means please use the actual bean((object)[here, ProductController]) when you use the ProductController
    private ProductController productController;

    //We are going to test the productController. So I didn't mock product controller. But I don't want to test the product service in the productController and I want to mock the productService. So, ue @MockitoBean
    //I am going to hardcoded the value of id whatever I'll get it from productService. Here I hardcoded the value of id = 1L
    //You can use @MockBean and @Qualifier("dbProductService") or @MockitoBean(name = "dbProductService") -> Everything come from Mockito
    //@MockBean
    //@Qualifier("dbProductService")

    @MockitoBean(name = "dbProductService") //mentioning with qualifier(which service have we used) -> This should be same as what service you mentioned as @Qualifier in ProductController. You mentioned "dbProductService", so, mentioned the same here. Don use "fakeStoreProductService"
    private ProductService productService;

    //For Positive Scenario Test Case
    @Test
    void getProductById() throws ProductNotFoundException {
        //Arrange - input, expected output, dependency
        //whatever productId you have given, no error. Because this id only pass to productController. Both expectedProduct and actualProduct has same id only pass. Here id = 11, then this only will pass.
        //Whatever id you change, the same id only pass in both expectedProduct and actualProduct. Check it in debug points and see in Threads & Variables
        Long productId = 15L;
        Product expectedProduct = new Product();
        expectedProduct.setId(productId);
        expectedProduct.setTitle("Iphone Pro Max Ultra");
        expectedProduct.setPrice(100000.0);
        //whenever get the single product of id = 1, then return id = 1, title = Iphone Pro Max Ultra, price = 100000.0
        when(productService.getSingleProduct(productId))
                .thenReturn(expectedProduct);

        //Act - actual output
        //Here, expectedProduct id is 11, but you gave 13L product id in actualProduct, so test case should fail. For product id = 13L, I didn't hard code the value here.  So, actualProduct = null product. ExpectedProduct != ActualProduct. So, testcases fails...
        Product actualProduct = productController.getProductById(productId);
        //  Product actualProduct = productController.getProductById(13L);

        //if the product is return as ResponseEntity, but you want product from response entity then mention .getBody() with the code (i.e)
        //  Product actualProduct = productController.getProductById(productId).getBody();

        //Assert - compare expected output and actual output
        assertNotNull(actualProduct);//here the test case fails here, it shows the error here because the expectedProduct is 11L but actualProduct is 13L, and it tells that it is the null product
        assertEquals(expectedProduct, actualProduct);
    }

    //For Positive Scenario Test Case
    @Test
    void getProductByIdAndCheckResponseEntity() throws ProductNotFoundException {
        //Arrange
        Long productId = 10L;
        Product expectedProduct = new Product();
        expectedProduct.setId(productId);
        expectedProduct.setTitle("Famous poetry Books");
        expectedProduct.setPrice(1000.0);
        when(productService.getSingleProduct(productId))
                .thenReturn(expectedProduct);

        //Act
        //Here, actual product returns a new product...see ProductController.java class(while understanding, just uncomment line 101 and 102 in productController.java)
        Product actualProduct = productController.getProductByIdAndCheckResponseEntity(productId).getBody();

        //Assert
        assertNotNull(actualProduct);
        assertEquals(expectedProduct, actualProduct);//here, the test case fails, shows the error in assertEquals, even though you set all the products but in product controller it returns a new Product
    }

    //For Negative Scenario Test Case
    @Test
    void testGetSingleProductNegative() throws ProductNotFoundException {
        //Scenario 1: there is no product id for -1, so it throws an exception. This is true. So,test case is passed
        //Scenario 2: If I mention actual product id is 1(see line no: 91),  then, is it throw an exception? Yes, again, because actually expected productId is also same as what I mention in product ID (i.e)1L.
                    //I said actual productId is also return the same productId for 1L(see in assertThrows(line no: 98))
                    //So, test case is passed here because actual goal for negative scenario test case is throws an exception
        //Scenario 3: If actual product id is -1L(line no: 92), then expected product id is actual good product = 1L (see in assertThrows), then will it throw an exception? No. It doesn't throw an exception.
                    //because assertThrows compare expected output for the product id for 1L but, I mentioned actual productId is -1L in line no:92, so, it throws an exception
                    //In scenario 3, I didn't test the case properly. So, test should have consistency what you have test for...
        long productId = -1L;
        //Arrange - input, expected output
        when(productService.getSingleProduct(productId))
                .thenThrow(ProductNotFoundException.class);

        //Act and Arrange
        assertThrows(
                ProductNotFoundException.class,
                ()-> productController.getProductById(productId/*1L*/)
        );
    }
    @Test
    void getAllProducts() {
    }

    @Test
    void getAllProductsAndCheckResponseEntity() {
    }

    @Test
    void getAllProductsByPages() {
    }

    @Test
    void deleteProductById() {
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