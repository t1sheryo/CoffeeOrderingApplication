package com.coffeeorder.app.repository;

import com.coffeeorder.app.entity.CoffeeOrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CoffeeOrderItemRepository extends JpaRepository<CoffeeOrderItemEntity, Long> {
    @Query("SELECT i FROM CoffeeOrderItemEntity i " +
            "JOIN FETCH i.coffeeType " +
            "WHERE i.coffeeOrder.id = :orderId")
    List<CoffeeOrderItemEntity> findAllByOrderIdWithType(Long orderId);
}
