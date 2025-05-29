package com.coffeeorder.app.repository;

import com.coffeeorder.app.entity.CoffeeOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrderEntity, Long> {
}
