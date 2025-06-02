package com.coffeeorder.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.Objects;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;

        CoffeeOrderItemEntity that = (CoffeeOrderItemEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    // т.к. hashCode() каждый раз вызывается но момент проведения какого-то действия,
    // то надо чтобы уже сохранённые сущности с id имели одинаковый хеш-код
    // на основе их id, а сущности, которые были только что созданы
    // и не имеют хеш-кода должны иметь уникальный хеш-код по сравнению с другими
    // только что созданными сущностями
    @Override
    public int hashCode(){
        return id != null ? Objects.hash(id) : System.identityHashCode(id);
    }
}
