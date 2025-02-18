package com.ecommerce.productservicedecember2024;

import com.ecommerce.productservicedecember2024.models.Category;
import com.ecommerce.productservicedecember2024.models.Product;
import com.ecommerce.productservicedecember2024.projections.ProductWithIdAndTitle;
import com.ecommerce.productservicedecember2024.repositories.CategoryRepository;
import com.ecommerce.productservicedecember2024.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceDecember2024ApplicationTests {
    //test the hql queries here
    //How Qualifier is used if we use @Autowired instead of writing the constructor dependency injection? @Autowired(@Qualifier = "dbProductService"). However, we don't need this...
    @Autowired //@Autowired doing the dependency injection for product repository
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }


    @Test
    @Transactional
    //Transactional - group of operation(fetching, save...)- this does not close the hibernate session until the entire function finishes.
    //So, if transactional, then it will be consistency, rollback the changes can perform here
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

        //We are going to fetch the product...
        Optional<Product> optionalProduct = productRepository.findById(10L);

        //After debug, we get in console as
        /*
        2025-01-22T13:29:21.628+05:30 TRACE 22752 --- [ProductServiceDecember2024] [           main] o.s.t.i.TransactionInterceptor           : Getting transaction for [org.springframework.data.jpa.repository.support.SimpleJpaRepository.findById]
        Hibernate:
        select
            p1_0.id,
            c1_0.id,
            c1_0.created_at,
            c1_0.description,
            c1_0.name,
            c1_0.updated_at,
            p1_0.created_at,
            p1_0.price,
            p1_0.title,
            p1_0.updated_at
        from
            product p1_0
        left join
            category c1_0
            on c1_0.id=p1_0.category_id
        where
            p1_0.id=?
         */

        Optional<Category> optionalCategory = categoryRepository.findById(1L);

        Category category = optionalCategory.get();
        if(category != null) {
            System.out.println(category.getName()); //one plus 13R
            System.out.println(category.getDescription());//one plus
            //For fetch type = eager in list<product> of category model, the following line works where we check in "threads and variables"(next to console)
            //but when we do fetch type = lazy, the following line not work, it shows some exception in "threads and variables"(next to console), so put @Transactional
            //System.out.println(category.getProducts());//Refer threads and variables
        }


        /*
        After doing (This is by default) fetch = FetchType.Lazy in List<Product> of Category model
        After debug, we get in console as...see because of fetch type = eager, they did not do the join operation
        Hibernate:
        select
            c1_0.id,
            c1_0.created_at,
            c1_0.description,
            c1_0.name,
            c1_0.updated_at
        from
            category c1_0
        where
            c1_0.id=?
         */

        //After adding fetch = FetchType.EAGER in List<Product> of Category model
        //Doing debug, we get in console as...see because of fetch type = eager, they did the join operation
        /* Hibernate:
        select
            c1_0.id,
            c1_0.created_at,
            c1_0.description,
            c1_0.name,
            c1_0.updated_at,
            p1_0.category_id,
            p1_0.id,
            p1_0.created_at,
            p1_0.price,
            p1_0.title,
            p1_0.updated_at
        from
            category c1_0
        left join
            product p1_0
        on c1_0.id=p1_0.category_id
        where
            c1_0.id=?*/

        System.out.println("Getting Products");

        System.out.println("DEBUG");

    }

}
