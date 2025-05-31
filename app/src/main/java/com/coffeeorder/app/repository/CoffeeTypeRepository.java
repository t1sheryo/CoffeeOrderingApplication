package com.coffeeorder.app.repository;

import com.coffeeorder.app.entity.CoffeeTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CoffeeTypeRepository extends JpaRepository<CoffeeTypeEntity, Long> {
    @Query("SELECT c FROM CoffeeTypeEntity c WHERE c.disabled IS NULL OR c.disabled <> 'Y'")
    List<CoffeeTypeEntity> findPermissibleCoffeeTypes();
}
