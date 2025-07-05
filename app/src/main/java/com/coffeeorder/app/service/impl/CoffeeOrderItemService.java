package com.coffeeorder.app.service.impl;

import com.coffeeorder.app.entity.CoffeeOrderItemEntity;
import com.coffeeorder.app.mapper.CoffeeOrderItemMapper;
import com.coffeeorder.app.mapper.CoffeeTypeDtoToCoffeeOrderItemDtoMapper;
import com.coffeeorder.app.model.CreateCoffeeOrderItemDTO;
import com.coffeeorder.app.model.ReturnCoffeeTypeDTO;
import com.coffeeorder.app.repository.CoffeeOrderItemRepository;
import com.coffeeorder.app.repository.CoffeeTypeRepository;
import com.coffeeorder.app.service.interfaces.CoffeeOrderItemServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CoffeeOrderItemService implements CoffeeOrderItemServiceInterface {
    private final CoffeeTypeService coffeeTypeService;
    private final CoffeeOrderItemRepository coffeeOrderItemRepository;
    private final ConfigService configService;
    private final CoffeeOrderItemMapper coffeeOrderItemMapper;
    private final CoffeeTypeDtoToCoffeeOrderItemDtoMapper mapper;

    @Autowired
    public CoffeeOrderItemService(
            CoffeeOrderItemRepository coffeeOrderItemRepository,
            ConfigService configService,
            CoffeeOrderItemMapper coffeeOrderItemMapper,
            CoffeeTypeService coffeeTypeService,
            CoffeeTypeDtoToCoffeeOrderItemDtoMapper mapper) {
        this.coffeeOrderItemRepository = coffeeOrderItemRepository;
        this.configService = configService;
        this.coffeeOrderItemMapper = coffeeOrderItemMapper;
        this.coffeeTypeService = coffeeTypeService;
        this.mapper = mapper;
    }

    /**
     @param dtos : Массив DTO объектов в заказе.
     @return : Суммарная стоимость всех объектов заказа.
     */
    @Override
    public void saveOrderItems(List<CreateCoffeeOrderItemDTO> dtos) {
        // toList возвращается полностью неизменяемый список.
        // И ссылка на него неизменяема и сами объекты внутри
        List<CoffeeOrderItemEntity> entities = dtos.stream()
                .map(coffeeOrderItemMapper::toEntity)
                .toList();
        coffeeOrderItemRepository.saveAll(entities);
    }

    @Override
    public List<CreateCoffeeOrderItemDTO> returnCoffeeOrderItemsDtos(
            List<Long> selectedCoffeeIds,
            Map<Long, Integer> typesQuantities) {
        List<CreateCoffeeOrderItemDTO> coffeeTypeDtos =
                coffeeTypeService.returnRequiredCoffeeTypesDtos(selectedCoffeeIds).stream()
                        .map(mapper::toCoffeeOrderItem)
                        .collect(Collectors.toList());

        for(CreateCoffeeOrderItemDTO dto : coffeeTypeDtos) {
            dto.setQuantityOfCups(typesQuantities.get(dto.getCoffeeTypeId()));
        }

        return coffeeTypeDtos;
    }
}
