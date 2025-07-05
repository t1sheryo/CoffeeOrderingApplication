package com.coffeeorder.app.service.impl;

import com.coffeeorder.app.mapper.CoffeeTypeMapper;
import com.coffeeorder.app.model.ReturnCoffeeTypeDTO;
import com.coffeeorder.app.repository.CoffeeTypeRepository;
import com.coffeeorder.app.service.interfaces.CoffeeTypeServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CoffeeTypeService implements CoffeeTypeServiceInterface {
    private final CoffeeTypeRepository coffeeTypeRepository;

    @Autowired
    public CoffeeTypeService(CoffeeTypeRepository coffeeTypeRepository) {
        this.coffeeTypeRepository = coffeeTypeRepository;
    }

    @Override
    public List<ReturnCoffeeTypeDTO> getAllCoffeeTypes() {
        var coffeeTypes = coffeeTypeRepository.findPermissibleCoffeeTypes().stream()
                .map(CoffeeTypeMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
        log.info("All permissible coffeeTypes: {}", coffeeTypes);
        return coffeeTypes;
    }

    @Override
    public List<ReturnCoffeeTypeDTO> returnRequiredCoffeeTypesDtos(List<Long> coffeeTypesIds) {
        List<ReturnCoffeeTypeDTO> dtos = coffeeTypeRepository.findByIdIn(coffeeTypesIds).stream()
                .map(CoffeeTypeMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
        return dtos;
    }
}
