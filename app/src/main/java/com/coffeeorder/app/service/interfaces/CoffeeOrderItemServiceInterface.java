package com.coffeeorder.app.service.interfaces;

import com.coffeeorder.app.model.CreateCoffeeOrderItemDTO;

import java.util.List;
import java.util.Map;

public interface CoffeeOrderItemServiceInterface {
    void saveOrderItems(List<CreateCoffeeOrderItemDTO> orderItems);
    List<CreateCoffeeOrderItemDTO> returnCoffeeOrderItemsDtos(List<Long> selectedCoffeeIds, Map<Long, Integer> typesQuantities);
}
