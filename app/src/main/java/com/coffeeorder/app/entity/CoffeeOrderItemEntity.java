package com.coffeeorder.app.entity;

// TODO: реализовать equals() и hashcode()

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(
        name = "CoffeeOrderItem",
        indexes = {
        @Index(name = "COI_I", columnList = "order_id"),
        @Index(name = "COI_3", columnList = "type_id")
})
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
