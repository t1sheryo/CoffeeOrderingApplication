package com.coffeeorder.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCoffeeOrderDTO {
    private String customerName;
    private String deliveryAddress;
    private Double orderCost;
}
