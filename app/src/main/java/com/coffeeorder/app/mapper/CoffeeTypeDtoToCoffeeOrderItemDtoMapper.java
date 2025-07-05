package com.coffeeorder.app.mapper;

import com.coffeeorder.app.model.ReturnCoffeeTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.coffeeorder.app.model.CreateCoffeeOrderItemDTO;

@Mapper(componentModel = "spring")
public interface CoffeeTypeDtoToCoffeeOrderItemDtoMapper {
    CoffeeTypeDtoToCoffeeOrderItemDtoMapper INSTANCE =
            Mappers.getMapper(CoffeeTypeDtoToCoffeeOrderItemDtoMapper.class);

    @Mapping(source = "id", target = "coffeeTypeId")
    @Mapping(source = "name", target = "coffeeTypeName")
    @Mapping(source = "price", target = "price")
    CreateCoffeeOrderItemDTO toCoffeeOrderItem(ReturnCoffeeTypeDTO returnCoffeeTypeDTO);
}
