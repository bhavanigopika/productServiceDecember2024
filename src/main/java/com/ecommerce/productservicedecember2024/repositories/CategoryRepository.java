package com.ecommerce.productservicedecember2024.repositories;

import com.ecommerce.productservicedecember2024.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    @Override
    Category save(Category category);

}
