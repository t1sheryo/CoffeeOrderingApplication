package com.coffeeorder.app.controller;

import com.coffeeorder.app.model.CreateCoffeeOrderDTO;
import com.coffeeorder.app.service.impl.CoffeeOrderItemService;
import com.coffeeorder.app.service.impl.CoffeeOrderService;
import com.coffeeorder.app.service.impl.ConfigService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.coffeeorder.app.model.CreateCoffeeOrderItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/api/order")
public class CoffeeOrderController {
    private final CoffeeOrderService coffeeOrderService;
    private final CoffeeOrderItemService coffeeOrderItemService;
    private final ConfigService configService;

    @Autowired
    public CoffeeOrderController(
            CoffeeOrderService coffeeOrderService,
            CoffeeOrderItemService coffeeOrderItemService,
            ConfigService configService) {
        this.coffeeOrderService = coffeeOrderService;
        this.coffeeOrderItemService = coffeeOrderItemService;
        this.configService = configService;
    }

    // @RequestParam конкретно инструктирует Spring,
    // как извлечь и преобразовать данные из HTTP-запроса
    // в соответствующий тип данных Java для аргумента метода.
    // Без этой аннотации Spring не знал бы,
    // откуда брать значение для selectedCoffeeIds
    // или allRequestParams
    @GetMapping("/orderDetails")
    public String enterOrderDetails(
            @RequestParam(name = "selectedCoffeeIds", required = false) List<Long> selectedCoffeeIds,
            @RequestParam Map<String, String> typesQuantitiesRaw,
            RedirectAttributes redirectAttributes,
            Model model) {
        log.info("GET /orderDetails - Selected Coffee IDs: {}", selectedCoffeeIds);
        log.info("GET /orderDetails - Raw Quantities: {}", typesQuantitiesRaw);

        if (selectedCoffeeIds == null || selectedCoffeeIds.isEmpty()) {
            log.warn("User should have selected Coffee IDs");
            redirectAttributes.addFlashAttribute("message", "User should have selected Coffee IDs");
            return "redirect:/api/coffeeTypes/typesList";
        }

        Map<Long, Integer> typesQuantities = typesQuantitiesRaw.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("quantity_"))
                .collect(Collectors.toMap(
                    entry -> Long.parseLong(entry.getKey().substring("quantity_".length())), // Извлекаем ID
                    entry -> {
                        try {
                            int quantity = Integer.parseInt(entry.getValue());
                            return Math.max(0, quantity); // Гарантируем неотрицательное количество
                        } catch (NumberFormatException e) {
                            log.error("Неверный формат количества для {}: {}", entry.getKey(), entry.getValue());
                            return 0; // Игнорируем или обрабатываем как ошибку
                        }
                    }
                ));

        List<Long> actualSelectedCoffeeIds = selectedCoffeeIds.stream()
                .filter(id -> typesQuantities.containsKey(id) && typesQuantities.get(id) > 0)
                .collect(Collectors.toList());

        if (actualSelectedCoffeeIds.isEmpty()) {
            log.warn("Выбранные товары не имеют допустимого количества или были отменены.");
            redirectAttributes.addFlashAttribute("errorMessage", "Пожалуйста, укажите положительное количество для выбранных товаров.");
            return "redirect:/api/coffeeTypes/typesList";
        }

        List<CreateCoffeeOrderItemDTO> orderItems =
                    coffeeOrderItemService.returnCoffeeOrderItemsDtos(actualSelectedCoffeeIds, typesQuantities);

        Double totalCost = coffeeOrderService.calculateOrderCost(orderItems);
        int xValue = 0, mValue = 0;

        model.addAttribute("orderItems", orderItems);
        model.addAttribute("totalCost", totalCost);
        model.addAttribute("xValue", xValue);
        model.addAttribute("mValue", mValue);

        model.addAttribute("selectedCoffeeIds", actualSelectedCoffeeIds);
        model.addAttribute("typesQuantities", typesQuantities);

        CreateCoffeeOrderDTO coffeeOrderDTO = new CreateCoffeeOrderDTO();
        coffeeOrderDTO.setOrderCost(totalCost);
        model.addAttribute("coffeeOrderDTO", coffeeOrderDTO);

        return "OrderPage";
    }

    @PostMapping("/orderCreation")
    public String createOrder(
            @Valid @ModelAttribute("coffeeOrderDTO") CreateCoffeeOrderDTO coffeeOrderDTO,
            BindingResult bindingResult,
            @RequestParam(name = "selectedCoffeeIds", required = false) List<Long> selectedCoffeeIds,
            @RequestParam Map<Long, Integer> typesQuantities,
            Model model,
            RedirectAttributes redirectAttributes
            ) {
        log.info("POST /orderCreation - Получены детали заказа для создания: {}", coffeeOrderDTO);

        List<CreateCoffeeOrderItemDTO> orderItems =
                coffeeOrderItemService.returnCoffeeOrderItemsDtos(selectedCoffeeIds, typesQuantities);

        if (bindingResult.hasErrors()) {
            log.warn("Ошибки валидации при оформлении заказа: {}", bindingResult.getAllErrors());

            // При ошибках валидации нужно снова заполнить модель всеми данными,
            // которые нужны для отображения OrderPage.
            // Используем данные из @RequestParam selectedCoffeeIds и typesQuantities
            // для повторного создания orderItems.

            double currentTotalCost = coffeeOrderService.calculateOrderCost(orderItems);
            int xValue = configService.getX();
            int mValue = configService.getM();

            model.addAttribute("orderItems", orderItems);
            model.addAttribute("totalCost", currentTotalCost);
            model.addAttribute("xValue", xValue);
            model.addAttribute("mValue", mValue);
            // Важно: снова добавляем selectedCoffeeIds и typesQuantities в модель
            // для повторного рендеринга скрытых полей формы
            model.addAttribute("selectedCoffeeIds", selectedCoffeeIds);
            model.addAttribute("typesQuantities", typesQuantities);


            model.addAttribute("errorMessage", "Пожалуйста, исправьте ошибки в форме.");
            return "OrderPage";
        }

        double verifiedTotalCost = coffeeOrderService.calculateOrderCost(orderItems);

        if (Math.abs(verifiedTotalCost - coffeeOrderDTO.getOrderCost()) > 0.01) {
            log.warn("Расхождение в стоимости заказа: переданная стоимость = {}, пересчитанная стоимость = {}. Используем пересчитанную.",
                     coffeeOrderDTO.getOrderCost(), verifiedTotalCost);
            coffeeOrderDTO.setOrderCost(verifiedTotalCost);
        }

        try {
            coffeeOrderItemService.saveOrderItems(orderItems);
            coffeeOrderService.saveOrder(coffeeOrderDTO);

            return "redirect:/orderConfirmationPage";
        } catch (Exception e) {
            log.error("Ошибка при сохранении заказа: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "Произошла ошибка при оформлении заказа: " + e.getMessage());

            String redirectUrl = "/api/order/orderDetails" +
                                 "?selectedCoffeeIds=" + selectedCoffeeIds.stream()
                                     .map(String::valueOf)
                                     .collect(Collectors.joining(",")) +
                                 typesQuantities.entrySet().stream()
                                     .map(entry -> "&quantity_" + entry.getKey() + "=" + entry.getValue())
                                     .collect(Collectors.joining());

            return "redirect:" + redirectUrl;
        }
    }
}
