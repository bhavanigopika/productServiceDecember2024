package com.ecommerce.productservicedecember2024.dto;

import java.util.Date;

public class GetProductDto extends  BaseModelDto{

    private String title;
    private Double price;
    private CategoryDto category;

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

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
}
