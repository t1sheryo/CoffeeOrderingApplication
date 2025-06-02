package com.coffeeorder.app.mapper;

import com.coffeeorder.app.entity.CoffeeOrderEntity;
import com.coffeeorder.app.entity.CoffeeOrderItemEntity;
import com.coffeeorder.app.model.CreateCoffeeOrderDTO;
import com.coffeeorder.app.model.CreateCoffeeOrderItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CoffeeOrderItemMapper {
    CoffeeOrderItemMapper mapper = Mappers.getMapper(CoffeeOrderItemMapper.class);

    @Mapping(source = "coffeeTypeId", target = "coffeeType.id")
    @Mapping(source = "coffeeOrderId", target = "coffeeOrder.id")
    CoffeeOrderItemEntity toEntity(CreateCoffeeOrderItemDTO dto);

    @Mapping(target = "price", ignore = true)
    @Mapping(target = "coffeeTypeName", ignore = true)
    @Mapping(source = "coffeeType.id", target = "coffeeTypeId")
    @Mapping(source = "coffeeOrder.id", target = "coffeeOrderId")
    CreateCoffeeOrderItemDTO toDto(CoffeeOrderItemEntity entity);
}
