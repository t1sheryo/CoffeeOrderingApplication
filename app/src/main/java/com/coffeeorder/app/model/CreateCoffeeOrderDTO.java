package com.coffeeorder.app.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCoffeeOrderDTO {
    @NotBlank(message = "Пожалуйста, введите ФИО.")
    private String customerName;

    @NotBlank(message = "Пожалуйста, введите адрес доставки.")
    private String deliveryAddress;

    @NotNull(message = "Стоимость заказа не может быть пустой.")
    @DecimalMin(value = "0.01", message = "Стоимость заказа должна быть больше нуля.")
    private Double orderCost;
}
