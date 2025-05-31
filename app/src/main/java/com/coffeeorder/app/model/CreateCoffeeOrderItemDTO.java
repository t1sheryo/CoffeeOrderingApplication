package com.coffeeorder.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCoffeeOrderItemDTO {
    private Long coffeeTypeId;
    private Long coffeeOrderId;
    private Integer quantityOfCups;
}
