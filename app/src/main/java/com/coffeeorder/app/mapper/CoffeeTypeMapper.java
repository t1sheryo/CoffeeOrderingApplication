package com.coffeeorder.app.mapper;

import com.coffeeorder.app.entity.CoffeeOrderEntity;
import com.coffeeorder.app.entity.CoffeeTypeEntity;
import com.coffeeorder.app.model.ReturnCoffeeTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CoffeeTypeMapper {
    CoffeeTypeMapper INSTANCE = Mappers.getMapper(CoffeeTypeMapper.class);

    CoffeeTypeEntity toEntity(ReturnCoffeeTypeDTO dto);

    ReturnCoffeeTypeDTO toDto(CoffeeTypeEntity entity);
}
