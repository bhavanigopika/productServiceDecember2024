package com.ecommerce.productservicedecember2024.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
}
/*
Product Service
1. create product
2. get product
3. update product
4. delete product

MySQL DB we have to use here

Client talk to -----> Product Service interact with  -----> Database(Product table: id, quantity, name, price)

We have to use FakeStore API to code our Product Service
  1. Why we use FakeStore API? Based on this, we are trying to understand the third party API. So, product service use third party API. Right now, not use database

Here, now

Client talk to -----> Product Service interact with  -----> FakeStore API (Product table: id, quantity, name, price)

FakeStore API gives product data. So, product service is proxy service and actual service is FakeStore API.
Later, we replace the fakestore API into Database. Simply, we can say, get the ready-made product data from FakeStore API instead of creating the database right now.

Learning today - Agenda: How we are going to call third party APIs from our product Service


 */