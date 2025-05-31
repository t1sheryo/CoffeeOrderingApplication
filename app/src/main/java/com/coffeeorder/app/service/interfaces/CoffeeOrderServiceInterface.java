package com.coffeeorder.app.service.interfaces;

import com.coffeeorder.app.entity.CoffeeOrderEntity;
import com.coffeeorder.app.model.CreateCoffeeOrderDTO;

public interface CoffeeOrderServiceInterface {
    void saveOrder(CreateCoffeeOrderDTO coffeeOrder);
}
