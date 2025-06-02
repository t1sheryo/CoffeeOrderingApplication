package com.coffeeorder.app.service.impl;

import com.coffeeorder.app.entity.CoffeeOrderItemEntity;
import com.coffeeorder.app.mapper.CoffeeOrderItemMapper;
import com.coffeeorder.app.model.CreateCoffeeOrderItemDTO;
import com.coffeeorder.app.repository.CoffeeOrderItemRepository;
import com.coffeeorder.app.service.interfaces.CoffeeOrderItemServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CoffeeOrderItemService implements CoffeeOrderItemServiceInterface {
    private final CoffeeOrderItemRepository coffeeOrderItemRepository;
    private final ConfigService configService;
    private final CoffeeOrderItemMapper coffeeOrderItemMapper;

    @Autowired
    public CoffeeOrderItemService(CoffeeOrderItemRepository coffeeOrderItemRepository, ConfigService configService, CoffeeOrderItemMapper coffeeOrderItemMapper) {
        this.coffeeOrderItemRepository = coffeeOrderItemRepository;
        this.configService = configService;
        this.coffeeOrderItemMapper = coffeeOrderItemMapper;
    }

    /**
     @param orderItems : Массив DTO объектов в заказе.
     @return : Суммарная стоимость всех объектов заказа.
     */
    @Override
    public Double saveOrderItems(List<CreateCoffeeOrderItemDTO> dtos) {
        Double orderTotalCost = 0.0;

        // toList возвращается полностью неизменяемый список.
        // И ссылка на него неизменяема и сами объекты внутри
        List<CoffeeOrderItemEntity> entities = dtos.stream()
                .map(coffeeOrderItemMapper::toEntity)
                .toList();
        coffeeOrderItemRepository.saveAll(entities);

        for(CreateCoffeeOrderItemDTO dto : dtos) {
            orderTotalCost += dto.getPrice();
        }

        return orderTotalCost;
    }
}
