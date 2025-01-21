package com.ecommerce.productservicedecember2024.inheritanceTypes.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "jt_instructors")
//The following line actually to define user_id as a foreign key
//id is primary key because we extend user
//So, table column  module, user_id
@PrimaryKeyJoinColumn(name = "user_id")
public class Instructor extends User{
    private String module;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
