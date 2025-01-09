package com.ecommerce.productservicedecember2024.inheritanceTypes.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "jt_mentors")
//The following line actually to define user_id as a foreign key
//id is primary key because we extend user
//So, table column will be id, user_id, company_name
@PrimaryKeyJoinColumn(name = "user_id")
public class Mentor extends User {
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

