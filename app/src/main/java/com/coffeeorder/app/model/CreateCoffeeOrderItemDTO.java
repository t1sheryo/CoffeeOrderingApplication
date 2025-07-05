package com.coffeeorder.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCoffeeOrderItemDTO {
    private Long coffeeTypeId;
    private Long coffeeOrderId;
    private String coffeeTypeName;
    private Integer quantityOfCups;
    private Double price;
}
