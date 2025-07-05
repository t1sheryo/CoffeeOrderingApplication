package com.coffeeorder.app.controller;


import com.coffeeorder.app.model.ReturnCoffeeTypeDTO;
import com.coffeeorder.app.service.impl.CoffeeTypeService;
import com.coffeeorder.app.service.impl.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/coffeeTypes")
public class CoffeeTypeController {
    private final CoffeeTypeService coffeeTypeService;
    private final ConfigService configService;

    @Autowired
    public CoffeeTypeController(CoffeeTypeService coffeeTypeService, ConfigService configService) {
        this.coffeeTypeService = coffeeTypeService;
        this.configService = configService;
    }

    @GetMapping("/typesList")
    public String returnAllCoffeeTypes(Model model) {
        List<ReturnCoffeeTypeDTO> coffeeTypes = coffeeTypeService.getAllCoffeeTypes();
        int nValue = configService.getN();
        log.info("Returning CoffeeTypes {} with N {}", coffeeTypes, nValue);

        model.addAttribute("coffeeTypes", coffeeTypes);
        model.addAttribute("nValue", nValue);

        if (model.containsAttribute("message")) {
            log.info("Flash message received: {}", model.getAttribute("message"));
        }

        return "CoffeeListPage";
    }
}
