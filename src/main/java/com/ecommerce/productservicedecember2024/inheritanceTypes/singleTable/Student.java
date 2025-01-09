package com.ecommerce.productservicedecember2024.inheritanceTypes.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "st_students")
@DiscriminatorValue(value = "1")
public class Student extends User {
    private Double psp;

    public Double getPsp() {
        return psp;
    }

    public void setPsp(Double psp) {
        this.psp = psp;
    }
}
