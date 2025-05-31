package com.coffeeorder.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// TODO: реализовать equals() и hashcode()

@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(
        name = "CoffeeOrder",
        indexes = {
        @Index(name = "CO_I1", columnList = "id")
})
public class CoffeeOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "order_date", nullable = false, updatable = false)
    private LocalDateTime orderDate;

    //@NotBlank(message = "Customer name should not be null or empty")
    @Size(max = 100, message = "Customer name length should not exceed {max} characters")
    @Column(length = 100, name = "name")
    private String customerName;

    @NotBlank(message = "Delivery address should not be null or empty")
    @Size(max = 200, message = "Delivery address length should not exceed {max} characters")
    @Column(length = 100, name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @Column(name = "cost")
    @PositiveOrZero(message = "Order cost should not be negative")
    private Double orderCost;
}
