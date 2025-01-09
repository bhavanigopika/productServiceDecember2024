package com.ecommerce.productservicedecember2024.inheritanceTypes.tablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_instructors")
//Table as id, email, name, module
public class Instructor extends User {
    private String module;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
