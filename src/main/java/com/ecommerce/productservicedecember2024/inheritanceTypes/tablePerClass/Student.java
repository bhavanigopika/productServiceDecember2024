package com.ecommerce.productservicedecember2024.inheritanceTypes.tablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_students")
//Here the table will be id,email,name,psp
public class Student extends User {
    private Double psp;

    public Double getPsp() {
        return psp;
    }

    public void setPsp(Double psp) {
        this.psp = psp;
    }
}
