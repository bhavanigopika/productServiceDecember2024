package com.ecommerce.productservicedecember2024;

import com.ecommerce.productservicedecember2024.controllers.ProductController;
import com.ecommerce.productservicedecember2024.models.Category;
import com.ecommerce.productservicedecember2024.models.Product;
import com.ecommerce.productservicedecember2024.projections.ProductWithIdAndTitle;
import com.ecommerce.productservicedecember2024.repositories.CategoryRepository;
import com.ecommerce.productservicedecember2024.repositories.ProductRepository;
import com.ecommerce.productservicedecember2024.services.ProductService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.Duration;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceDecember2024ApplicationTests {

    @Qualifier("dbProductService")
    @Autowired
    private ProductService productService;

    /*****/

//    //test the hql queries here
//    //How Qualifier is used if we use @Autowired instead of writing the constructor dependency injection? @Autowired(@Qualifier = "dbProductService"). However, we don't need this...
//    @Autowired //@Autowired doing the dependency injection for product repository
//    private ProductRepository productRepository;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Test
//    void contextLoads() {
//    }
//
//
//    @Test
//    @Transactional
//    //Transactional - group of operation(fetching, save...)- this does not close the hibernate session until the entire function finishes.
//    //So, if transactional, then it will be consistency, rollback the changes can perform here
//    void testDBQueries() {
//        //call the method (randomSearchMethodForProduct) to test using productRepository and return type is List<ProductWithIdAndTitle>
//        List<ProductWithIdAndTitle> productWithIdAndTitleList = productRepository.randomSearchMethodForProduct();
//
//        //Go with each product id and title in the list
//        for(ProductWithIdAndTitle productWithIdAndTitle : productWithIdAndTitleList) {
//            System.out.println(productWithIdAndTitle.getId() + " " + productWithIdAndTitle.getTitle());
//        }
//
//        //After debug, we get in console as
//        /*
//        Hibernate:
//             select
//                p1_0.id,
//                p1_0.title
//            from
//                product p1_0
//
//        2025-01-16T13:01:30.580+05:30 TRACE 17040 --- [ProductServiceDecember2024] [           main] o.s.beans.CachedIntrospectionResults     : Found bean property 'title' of type [java.lang.String]
//                1 Google Pixel Phone
//                2 New Novel Section
//                3 Macbook Pro 2 laptop
//                4 Big BigZ headphone
//                5 Sony headphone
//                6 Sony headphone
//                7 Noise Airwave headphone
//                12 One Plus
//
//         So, here take 2 attributes. Hence, lesser memory only it takes instead of getting all attributes
//         */
//
//        //call the method (nativeSearchMethodForProduct) to test using productRepository and return type is List<ProductWithIdAndTitle>
//        List<ProductWithIdAndTitle> productWithIdAndTitleList1 =  productRepository.nativeSearchMethodForProduct();
//        //Go with each product id and title in the list
//        for(ProductWithIdAndTitle productWithIdAndTitle : productWithIdAndTitleList1) {
//            System.out.println(productWithIdAndTitle.getId() + " " + productWithIdAndTitle.getTitle());
//        }
//
//        //After debug, we get in console as
//
//        /*
//        Hibernate:
//            select
//                p.id as id,
//                p.title as title
//            from
//                product p
//
//        2025-01-16T19:27:27.030+05:30 TRACE 66360 --- [ProductServiceDecember2024] [           main] o.s.aop.framework.JdkDynamicAopProxy     : Creating JDK dynamic proxy: SingletonTargetSource for target object [org.springframework.data.jpa.repository.query.AbstractJpaQuery$TupleConverter$TupleBackedMap@53fd061d]
//
//        1 Google Pixel Phone
//        2 New Novel Section
//        3 Macbook Pro 2 laptop
//        4 Big BigZ headphone
//        5 Sony headphone
//        6 Sony headphone
//        7 Noise Airwave headphone
//        12 One Plus
//
//         */
//
//        //We are going to fetch the product...
//        Optional<Product> optionalProduct = productRepository.findById(10L);
//
//        //After debug, we get in console as
//        /*
//        2025-01-22T13:29:21.628+05:30 TRACE 22752 --- [ProductServiceDecember2024] [           main] o.s.t.i.TransactionInterceptor           : Getting transaction for [org.springframework.data.jpa.repository.support.SimpleJpaRepository.findById]
//        Hibernate:
//        select
//            p1_0.id,
//            c1_0.id,
//            c1_0.created_at,
//            c1_0.description,
//            c1_0.name,
//            c1_0.updated_at,
//            p1_0.created_at,
//            p1_0.price,
//            p1_0.title,
//            p1_0.updated_at
//        from
//            product p1_0
//        left join
//            category c1_0
//            on c1_0.id=p1_0.category_id
//        where
//            p1_0.id=?
//         */
//
//        Optional<Category> optionalCategory = categoryRepository.findById(1L);
//
//        Category category = optionalCategory.get();
//        if(category != null) {
//            System.out.println(category.getName()); //one plus 13R
//            System.out.println(category.getDescription());//one plus
//            //For fetch type = eager in list<product> of category model, the following line works where we check in "threads and variables"(next to console)
//            //but when we do fetch type = lazy, the following line not work, it shows some exception in "threads and variables"(next to console), so put @Transactional
//            //System.out.println(category.getProducts());//Refer threads and variables
//        }
//
//
//        /*
//        After doing (This is by default) fetch = FetchType.Lazy in List<Product> of Category model
//        After debug, we get in console as...see because of fetch type = eager, they did not do the join operation
//        Hibernate:
//        select
//            c1_0.id,
//            c1_0.created_at,
//            c1_0.description,
//            c1_0.name,
//            c1_0.updated_at
//        from
//            category c1_0
//        where
//            c1_0.id=?
//         */
//
//        //After adding fetch = FetchType.EAGER in List<Product> of Category model
//        //Doing debug, we get in console as...see because of fetch type = eager, they did the join operation
//        /* Hibernate:
//        select
//            c1_0.id,
//            c1_0.created_at,
//            c1_0.description,
//            c1_0.name,
//            c1_0.updated_at,
//            p1_0.category_id,
//            p1_0.id,
//            p1_0.created_at,
//            p1_0.price,
//            p1_0.title,
//            p1_0.updated_at
//        from
//            category c1_0
//        left join
//            product p1_0
//        on c1_0.id=p1_0.category_id
//        where
//            c1_0.id=?*/
//
//        System.out.println("Getting Products");
//
//        System.out.println("DEBUG");
//
//    }

    //Let's understand testing...
    @Test
    public void testAddition(){
        //3A framework to write test cases. 3A - Arrange, Act, Assert
        //A - Arrange => arrange the input, output and dependency here
        int a = 2;
        int b = 3;
        int expectedResult = 5;

        //A - Act => actual output
        int actualResult = addition(a, b);

        //A - Assert or check if everything is correct => compare the actual result and expected result
        //assert expectedResult == actualResult;

        //Now, instead of above line, we should write asserEquals because we can check for matching of 2 strings or 2 objects (or) any variants
        assertEquals(expectedResult, actualResult);

        String c = "Vidhu";
        String d = "Vidhu";
        //assertEquals d -> expectedResult, c -> actualResult
        assertEquals(d, c);

        //make sure that "a" is not equal to "b" after the execution has completed
        assertNotEquals(a, b);
    }

    @Test
    public void productServiceTest(){
        //make sure that p is not null
        //Product p = productService.getProduct(1); //getProduct with id = 1
        //assertNotNull(p);
        //e.g., if p is null, then put the title for p
        //assertEquals(p.title, "Magazine");

        //process should finish within this duration -> duration should be in millisecond. Here, we give 1000ms = 1 sec
        assertTimeout(Duration.ofMillis(1000),
                () -> productService.getSingleProduct(1L) ); //yes, it works and tests passed

        //If not complete in 1 sec then fail it
        /*
        assertTimeout(Duration.ofMillis(1),
                () -> productService.getSingleProduct(1L) );//yes, here tests failed -> execution exceeded timeout of 1 ms by 3ms (i.e) I say here if it crosses 1 ms, then it should fail, here it pass over 3ms

         */
    }

    private int addition(int a, int b){
        return a + b;//This works fine bec expectedResult = actualResult
        //return a + b + 1;//Here, tests failed because expectedResult != actualResult
    }
}
