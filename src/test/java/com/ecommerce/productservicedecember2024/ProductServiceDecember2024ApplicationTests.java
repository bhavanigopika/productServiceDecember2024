package com.ecommerce.productservicedecember2024;

import com.ecommerce.productservicedecember2024.projections.ProductWithIdAndTitle;
import com.ecommerce.productservicedecember2024.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceDecember2024ApplicationTests {
    //test the hql queries here
    @Autowired //@Autowired doing the dependency injection for product repository
    ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testDBQueries() {
        //call the method (randomSearchMethodForProduct) to test using productRepository and return type is List<ProductWithIdAndTitle>
        List<ProductWithIdAndTitle> productWithIdAndTitleList = productRepository.randomSearchMethodForProduct();

        //Go with each product id and title in the list
        for(ProductWithIdAndTitle productWithIdAndTitle : productWithIdAndTitleList) {
            System.out.println(productWithIdAndTitle.getId() + " " + productWithIdAndTitle.getTitle());
        }

        //After debug, we get in console as
        /*
        Hibernate:
             select
                p1_0.id,
                p1_0.title
            from
                product p1_0

        2025-01-16T13:01:30.580+05:30 TRACE 17040 --- [ProductServiceDecember2024] [           main] o.s.beans.CachedIntrospectionResults     : Found bean property 'title' of type [java.lang.String]
                1 Google Pixel Phone
                2 New Novel Section
                3 Macbook Pro 2 laptop
                4 Big BigZ headphone
                5 Sony headphone
                6 Sony headphone
                7 Noise Airwave headphone
                12 One Plus

         So, here take 2 attributes. Hence, lesser memory only it takes instead of getting all attributes
         */

        //call the method (nativeSearchMethodForProduct) to test using productRepository and return type is List<ProductWithIdAndTitle>
        List<ProductWithIdAndTitle> productWithIdAndTitleList1 =  productRepository.nativeSearchMethodForProduct();
        //Go with each product id and title in the list
        for(ProductWithIdAndTitle productWithIdAndTitle : productWithIdAndTitleList1) {
            System.out.println(productWithIdAndTitle.getId() + " " + productWithIdAndTitle.getTitle());
        }

        //After debug, we get in console as

        /*
        Hibernate:
            select
                p.id as id,
                p.title as title
            from
                product p

        2025-01-16T19:27:27.030+05:30 TRACE 66360 --- [ProductServiceDecember2024] [           main] o.s.aop.framework.JdkDynamicAopProxy     : Creating JDK dynamic proxy: SingletonTargetSource for target object [org.springframework.data.jpa.repository.query.AbstractJpaQuery$TupleConverter$TupleBackedMap@53fd061d]

        1 Google Pixel Phone
        2 New Novel Section
        3 Macbook Pro 2 laptop
        4 Big BigZ headphone
        5 Sony headphone
        6 Sony headphone
        7 Noise Airwave headphone
        12 One Plus

         */

        System.out.println("DEBUG");
    }

}
