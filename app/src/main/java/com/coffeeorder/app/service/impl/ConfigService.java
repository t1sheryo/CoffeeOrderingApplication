package com.coffeeorder.app.service.impl;


import com.coffeeorder.app.entity.ConfigurationParamsEntity;
import com.coffeeorder.app.repository.ConfigurationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Класс для хранения распаршенных данных из конфига

@Slf4j
@Service
public class ConfigService {
    private final Map<String, Integer> configValues = new HashMap<>();
    private static final int DEFAULT_N_VALUE = 5;
    private static final int DEFAULT_X_VALUE = 10;
    private static final int DEFAULT_M_VALUE = 2;

    @Autowired
    public ConfigService(ConfigurationRepository configurationRepository) {
        List<ConfigurationParamsEntity> configs = configurationRepository.findAll();

        for(ConfigurationParamsEntity config : configs) {
            try {
                int value = Integer.parseInt(config.getValue());
                configValues.put(config.getId(), value);
            } catch (NumberFormatException e) {
                log.error(e.getMessage());
            }
        }
    }

    public int getN() {
        return configValues.getOrDefault("n", DEFAULT_N_VALUE);
    }

    public int getX() {
        return configValues.getOrDefault("x", DEFAULT_X_VALUE);
    }

    public int getM() {
        return configValues.getOrDefault("m", DEFAULT_M_VALUE);
    }
}
