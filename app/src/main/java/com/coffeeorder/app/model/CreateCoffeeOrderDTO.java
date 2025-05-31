package com.coffeeorder.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCoffeeOrderDTO {
    private String customerName;
    private String deliveryAddress;
    private Double orderCost;
}
