package com.ecommerce.productservicedecember2024.services;

import com.ecommerce.productservicedecember2024.dto.FakeStoreProductDto;
import com.ecommerce.productservicedecember2024.exceptions.ProductNotFoundException;
import com.ecommerce.productservicedecember2024.models.Category;
import com.ecommerce.productservicedecember2024.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    /*
    I am not creating a restTemplate object again and again. So, I am getting it from application context where spring already have restTemplate here.
    And I defined that part (by saying some annotation to spring) in ApplicationConfig class. That's why I am initializing the restTemplate here through constructor like this
     */
    RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {//mark rest template as bean
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
    /*To call third party API(http request), we can use RestTemplate which is provided by Spring
    no need to create object of Rest Template each and every time. So, take RestTemplate at outside and marked it as bean. Because, if tomorrow, we want to change this "restTemplate" as some other,
    then entire things could change
    (Think about Bank API interface).
    So, we will create a class called ApplicationConfig to define RestTemplate*/

        //FakeStoreProductDto.class - I will provide the class of mapping (i.e) responseType...My responseType is like FakeStoreProductDto and not like Product class.
        //So, after getting the response from fakeStoreProductDto, I set it into my Product class. Finally, I will return this product

        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class);

        //throw new RuntimeException("This is an Exception. This is thrown from Product Service");

        //throw new ArithmeticException();//Goes to GlobalExceptionHandler and get the message

        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException("The product id " + productId + " does not exist");
        }

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    public Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();

        //convert fakeStoreProductDto to my Product
        product.setId(fakeStoreProductDto.getId());
        //responseType of category is String in fakeStore API, that's why I created as private String Category but for me,
        //my category is Category Type. So, I created a new object of category. In category class, I have created the constructor to initialize the object
        product.setCategory(new Category(fakeStoreProductDto.getCategory(), fakeStoreProductDto.getDescription()));

        //or
        //If I didn't create the constructor in category then I can set the name and description from fakeStoreProductDto
        /*
        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getDescription());
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        */

        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());

        return product;
    }

    @Override
    public List<Product> getAllProducts() {

        //List - ArrayList, LinkedList, Vector, Stack extends Vector
        //we want work like list but not generics, then we use Array, so instead of List<FakeStoreProductDto>, we can use array of FakeStoreProductDto[], so that no problem of generics will arise here
        //During run time, everything is object(raw type). FakeStoreProductDto is also converted to object during run time

        //List<FakeStoreProductDto> fspDtos = restTemplate.getForObject("https://fakestoreapi.com/products", List<FakeStoreProductDto>.class);

        //like int arr[] = new int[20];
        //size (say, 2o) comes from fake store API.
        FakeStoreProductDto fspDtos[] = restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fspDtos){
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        //PATCH
        /*
        //Here also patch object only refer but with detail, and you refer restTemplate.class(source code + documentation)
        a) RequestCallback requestCallback = this.httpEntityCallback(request, responseType);
        this - restTemplate
        request - object that I am passing (i.e) Product
        responseType - FakeStoreProductDto.class

        b) HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor(responseType, this.getMessageConverters());
        T - FakeStoreProductDto, this - restTemplate

        c) return (T)this.execute(url, HttpMethod.POST, requestCallback, responseExtractor, (Map)uriVariables);
        this - restTemplate, instead of post, use PATCH request
        */

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        //now, it returns fakeStoreProductDto because we mentioned, responseType would be FakeStoreProductDto.class
        FakeStoreProductDto fakeStoreProductDto = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PATCH, requestCallback, responseExtractor);
        //we convert it into product
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);

        //or directly use patchForObject
        /*request - object that I am passing (i.e) Product
          responseType - FakeStoreProductDto.class*/
        /*
        FakeStoreProductDto fakeStoreProductDto1 = restTemplate.patchForObject("https://fakestoreapi.com/products" + id, product, FakeStoreProductDto.class);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto1);
        */
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        //now, it returns fakeStoreProductDto because we mentioned, responseType would be FakeStoreProductDto.class
        FakeStoreProductDto fakeStoreProductDto = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        //we convert it into product
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);

    }
}
