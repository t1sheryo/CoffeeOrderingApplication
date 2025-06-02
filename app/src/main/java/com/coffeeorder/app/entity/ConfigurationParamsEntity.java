package com.coffeeorder.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;

// объекты данного класса не должны вводиться пользователем,
// а должны браться только из существующей таблицы БД

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "Configuration")
public class ConfigurationParamsEntity {
    @Id
    private String id;

    @Size(max = 30, message = "Value string length should not exceed {max} characters")
    @Column(name = "value", length = 30)
    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfigurationParamsEntity that = (ConfigurationParamsEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? Objects.hash(id) : System.identityHashCode(this);
    }
}
