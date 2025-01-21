package com.ecommerce.productservicedecember2024.inheritanceTypes.singleTable;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity(name = "st_users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//table have user_type(dtype), id, email, name, module, company_name, psp
//I can annotate dtype = student, instructor, mentor as discriminatorType = String or Discriminator Type = Integer  like student = 1, instructor = 2, mentor = 3
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "0")
public class User {
    @Id
    private int id;
    private String name;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
