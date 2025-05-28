package com.coffeeorder.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

// TODO: реализовать equals() и hashcode()

@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Entity
@Table(name = "CoffeeType")
public class CoffeeTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Coffee type name should not be null or empty")
    @Size(max = 200, message = "Coffee type name should not exceed {max} characters")
    @Column(length = 200, name = "type_name", nullable = false)
    private String name;

    @NotNull
    @Positive(message = "Coffee type price should be positive")
    private Double price;

    private Character disabled;
}
