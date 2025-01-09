package com.ecommerce.productservicedecember2024.inheritanceTypes.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "st_instructors")
@DiscriminatorValue(value = "2")
public class Instructor extends User {
    private String module;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
