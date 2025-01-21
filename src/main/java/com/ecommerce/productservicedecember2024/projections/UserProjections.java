package com.ecommerce.productservicedecember2024.projections;

public interface UserProjections {
    //inside the interface, I can add multiple interfaces

    interface BasicInformation {
        String getName();
        String getEmail();
    }

    interface DetailedInformation {
        String getName();
        String getEmail();
        String getAddress();
    }
}

/*

    Projections - Projections in Spring Boot provide a way to retrieve only a subset of fields from an entity.
                  This is particularly useful in optimizing performance, reducing memory usage, and securing data access
                  by fetching only the necessary fields

    First way is to keep related projections(user related projections) organized in one place(user projections)
    Easy to navigate to projections for a specific entity
    Cons: File can grow large if we have too many projections

    Second way is separate interfaces for each projection(productWithIdAndTitle) - one file per projection
    That is BasicInformation is one projection and other one is DetailedInformation
    Pros: Clean separation of concerns, easy to read smaller interfaces
    Cons: package gets increased

    Third way is you can use nested interfaces(BasicInformation, Detailed Information)  with descriptive names for the projections(User Projections)

    Fourth way is to do dynamic projections using DTO
    @Query("Select * form User u")
    List<UserDTO> findUsers();
 */