package com.ecommerce.productservicedecember2024.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//@Getter(AccessLevel.PUBLIC) // - All getters are public now
//@Setter(AccessLevel.PRIVATE) //- All setters level are private
//@Getter
//@Setter
@Entity
//table as id, created_at, updated_at, price, title, category_id
public class Product extends BaseModel {
    private String title;
    private Double price;
    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(String title, Double price, Category category) {
        this.title = title;
        this.price = price;
        this.category = category;
    }

    //boilerplate code
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
/*

Cardinality:

For 1 product(say, product id = 10), we get only 1 category either electronics, jewelery, men's clothing, women's clothing
for 1 category, we get multiple products  may be 1,3, 5 or 2, 5
Example, say we have 10 products
product id : category
1 : electronics
2 : jewelery
3 : men's clothing
4 : women's clothing
5 : men's clothing
6 : electronics
7 : women's clothing
8 : electronics
9 : jewelery
10 : jewelery
Product : Category
1 : 1
m : 1
------
m : 1
------

 */

/*
Why private? We don't want anyone to use from outside (i.e) Encapsulate = Encapsulation
If anybody wants to use, that's why we use here as Getter and Setter - we want to provide access but not provide direct access. So, provide access by getter and setter


1 "jeans"
2 "shirts"
3 "pen"
4 "mouse"

10^9(1000,000,000)

Enum can use only limited number of item...Hence, not use private enum Category;
So, category item will not be String, Enum. It should be Entity.
Category will be a table. Category are shirt, jeans, watch, sofa, laptop, books
Category:
Category name, Category id, description
If tomorrow new entry comes, then we add one more row in a database

 */