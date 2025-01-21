package com.ecommerce.productservicedecember2024.inheritanceTypes.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "jt_students")
//The following line actually to define user_id as a foreign key
//id is primary key because we extend user
//So, table column  psp, user_id
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {
    private Double psp;

    public Double getPsp() {
        return psp;
    }

    public void setPsp(Double psp) {
        this.psp = psp;
    }
}
