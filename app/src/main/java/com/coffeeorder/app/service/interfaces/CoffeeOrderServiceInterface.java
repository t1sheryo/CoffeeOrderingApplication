package com.coffeeorder.app.service.interfaces;

import com.coffeeorder.app.entity.CoffeeOrderEntity;
import com.coffeeorder.app.model.CreateCoffeeOrderDTO;
import com.coffeeorder.app.model.CreateCoffeeOrderItemDTO;

import java.util.List;

public interface CoffeeOrderServiceInterface {
    void saveOrder(CreateCoffeeOrderDTO coffeeOrder);
    Double calculateOrderCost(List<CreateCoffeeOrderItemDTO> orderItems);
}
