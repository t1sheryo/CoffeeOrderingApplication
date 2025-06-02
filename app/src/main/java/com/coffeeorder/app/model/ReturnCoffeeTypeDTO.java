package com.coffeeorder.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReturnCoffeeTypeDTO {
    private Long id;
    private String name;
    private Double price;
}
