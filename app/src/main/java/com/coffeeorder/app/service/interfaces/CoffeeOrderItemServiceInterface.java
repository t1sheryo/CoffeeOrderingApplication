package com.coffeeorder.app.service.interfaces;

import com.coffeeorder.app.model.CreateCoffeeOrderItemDTO;

import java.util.List;

public interface CoffeeOrderItemServiceInterface {
    Double saveOrderItems(List<CreateCoffeeOrderItemDTO> orderItems);
}
