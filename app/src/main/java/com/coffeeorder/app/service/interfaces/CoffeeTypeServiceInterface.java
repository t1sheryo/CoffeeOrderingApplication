package com.coffeeorder.app.service.interfaces;

import com.coffeeorder.app.entity.CoffeeTypeEntity;
import com.coffeeorder.app.model.ReturnCoffeeTypeDTO;

import java.util.List;

public interface CoffeeTypeServiceInterface {
    List<ReturnCoffeeTypeDTO> getAllCoffeeTypes();
    List<ReturnCoffeeTypeDTO> returnRequiredCoffeeTypesDtos(List<Long> coffeeTypesIds);
}
