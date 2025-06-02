package com.coffeeorder.app.service.impl;

import com.coffeeorder.app.entity.CoffeeOrderEntity;
import com.coffeeorder.app.mapper.CoffeeOrderMapper;
import com.coffeeorder.app.model.CreateCoffeeOrderDTO;
import com.coffeeorder.app.repository.CoffeeOrderRepository;
import com.coffeeorder.app.service.interfaces.CoffeeOrderServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CoffeeOrderService implements CoffeeOrderServiceInterface {
    private final CoffeeOrderRepository coffeeOrderRepository;
    private final CoffeeOrderMapper coffeeOrderMapper;

    public CoffeeOrderService(CoffeeOrderMapper coffeeOrderMapper, CoffeeOrderRepository coffeeOrderRepository) {
        this.coffeeOrderRepository = coffeeOrderRepository;
        this.coffeeOrderMapper = coffeeOrderMapper;
    }

    @Override
    public void saveOrder(CreateCoffeeOrderDTO coffeeOrder) {
        CoffeeOrderEntity entity = coffeeOrderMapper.toEntity(coffeeOrder);
        coffeeOrderRepository.save(entity);
    }
}
