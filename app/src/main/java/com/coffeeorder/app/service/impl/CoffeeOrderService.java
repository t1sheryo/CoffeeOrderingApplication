package com.coffeeorder.app.service.impl;

import com.coffeeorder.app.model.CreateCoffeeOrderDTO;
import com.coffeeorder.app.service.interfaces.CoffeeOrderServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CoffeeOrderService implements CoffeeOrderServiceInterface {
    @Override
    public void saveOrder(CreateCoffeeOrderDTO coffeeOrder) {

    }
}
