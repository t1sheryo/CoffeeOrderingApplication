package com.coffeeorder.app.repository;

import com.coffeeorder.app.entity.ConfigurationParamsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<ConfigurationParamsEntity, String> {
}
