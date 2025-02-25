package com.ecommerce.productservicedecember2024.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/*
For redis(like db), we implements Serializable - serialize the particular product. Serialize it by "Redis template".
a) To convert into json from product or category object (i.e) serialize.
b) convert back into product or category from json(i.e) deserialize
*/

//@Getter
//@Setter
@MappedSuperclass
public class BaseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//identity - auto increment
    private Long id;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
