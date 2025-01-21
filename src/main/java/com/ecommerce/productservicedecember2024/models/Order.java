package com.ecommerce.productservicedecember2024.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

/*@Entity
public class Order {
    @Id
    private int id;
    private String productName;
    @ManyToOne
    private Customer customer;
}*/
/*
Customer:Order
1:m -> 1 customer can make multiple order
1:1 -> 1 order for 1 customer only
-----
m:m
-----
 */