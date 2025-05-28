package com.coffeeorder.app.entity;

// TODO: реализовать equals() и hashcode()

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Entity
@Table(name = "CoffeeOrderItem")
public class CoffeeOrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_id", nullable = false)
    private CoffeeTypeEntity coffeeType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private CoffeeOrderEntity coffeeOrder;

    @Column(name = "quantity")
    @PositiveOrZero(message = "Quantity of cups should not be negative")
    private Integer quantityOfCups;
}
