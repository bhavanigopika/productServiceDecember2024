package com.ecommerce.productservicedecember2024.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

/*@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<Order> orderList = new ArrayList<>();
}*/
/*
Customer:Order
1:m -> 1 customer can make multiple order
1:1 -> 1 order for 1 customer only
-----
1:m
-----

CascadeType.REMOVE - This will work here, because if we remove the customer, then corresponding orders also gets deleted
here, no need to maintain the order because customer itself gets deleted. Also, we've customer:order is one-to-many relationship
 */