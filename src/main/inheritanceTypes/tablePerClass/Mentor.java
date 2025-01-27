package com.ecommerce.productservicedecember2024.inheritanceTypes.tablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_mentors")
//Here the table will be id,email,name,company_name
public class Mentor extends User {
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

