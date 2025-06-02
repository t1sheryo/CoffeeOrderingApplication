package com.coffeeorder.app.service.impl;

import com.coffeeorder.app.model.CreateCoffeeOrderItemDTO;
import com.coffeeorder.app.repository.CoffeeOrderItemRepository;
import com.coffeeorder.app.service.interfaces.CoffeeOrderItemServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CoffeeOrderItemService implements CoffeeOrderItemServiceInterface {
    private final CoffeeOrderItemRepository coffeeOrderItemRepository;
    private final ConfigService configService;

    @Autowired
    public CoffeeOrderItemService(CoffeeOrderItemRepository coffeeOrderItemRepository, ConfigService configService) {
        this.coffeeOrderItemRepository = coffeeOrderItemRepository;
        this.configService = configService;
    }

    /**
     @param orderItems : Массив DTO объектов в заказе.
     @return : Возвращает суммарную стоимость всех объектов заказа.
     */
    @Override
    public Double saveOrderItems(List<CreateCoffeeOrderItemDTO> orderItems) {
        // FIXME: в этом методе считывать данные из конфига и подсчитывать стоимость заказа

        return 0.0;
    }
}
