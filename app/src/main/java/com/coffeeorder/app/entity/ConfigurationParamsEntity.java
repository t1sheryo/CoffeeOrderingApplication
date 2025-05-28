package com.coffeeorder.app.entity;

// TODO: реализовать equals() и hashcode()

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Entity
@Table(name = "Configuration")
public class ConfigurationParamsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30, message = "Value string length should not exceed {max} characters")
    @Column(name = "value", length = 30)
    private String value;
}
