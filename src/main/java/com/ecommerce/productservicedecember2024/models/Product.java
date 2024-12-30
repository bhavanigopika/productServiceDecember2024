package com.ecommerce.productservicedecember2024.models;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

//@Getter(AccessLevel.PUBLIC) // - All getters are public now
//@Setter(AccessLevel.PRIVATE) //- All setters level are private
//@Getter
//@Setter
public class Product extends BaseModel {
    private String title;
    private double price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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