package com.coffeeorder.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
        name = "coffee_type",
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoffeeTypeEntity that = (CoffeeTypeEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    // т.к. hashCode() каждый раз вызывается но момент проведения какого-то действия,
    // то надо чтобы уже сохранённые сущности с id имели одинаковый хеш-код
    // на основе их id, а сущности, которые были только что созданы
    // и не имеют хеш-кода должны иметь уникальный хеш-код по сравнению с другими
    // только что созданными сущностями
    @Override
    public int hashCode() {
        return id != null ? Objects.hash(id) : System.identityHashCode(this);
    }
}
