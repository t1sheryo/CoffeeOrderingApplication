package com.coffeeorder.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;

        CoffeeOrderEntity that = (CoffeeOrderEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    // т.к. hashCode() каждый раз вызывается но момент проведения какого-то действия,
    // то надо чтобы уже сохранённые сущности с id имели одинаковый хеш-код
    // на основе их id, а сущности, которые были только что созданы
    // и не имеют хеш-кода должны иметь уникальный хеш-код по сравнению с другими
    // только что созданными сущностями
    @Override
    public int hashCode(){
        return id != null ? Objects.hash(id) : System.identityHashCode(this);
    }
}
