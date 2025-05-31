package com.coffeeorder.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

// TODO: реализовать equals() и hashcode()

@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(
        name = "CoffeeType",
        indexes = {
        @Index(name = "CT_I", columnList = "id")
})
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
