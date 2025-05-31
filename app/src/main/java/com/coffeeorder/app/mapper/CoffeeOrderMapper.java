package com.coffeeorder.app.mapper;

import com.coffeeorder.app.entity.CoffeeOrderEntity;
import com.coffeeorder.app.model.CreateCoffeeOrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CoffeeOrderMapper {
    CoffeeOrderMapper INSTANCE = Mappers.getMapper(CoffeeOrderMapper.class);

    CoffeeOrderEntity toEntity(CreateCoffeeOrderDTO dto);

    CreateCoffeeOrderDTO toDto(CoffeeOrderEntity entity);
}
