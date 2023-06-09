package com.joel.food.api.controller;

import com.joel.food.domain.repository.KitchenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final KitchenRepository kitchenRepository;

//    @GetMapping("/kitchen")
//    public List<Kitchen> findByName(@RequestParam String name) {
//        return kitchenRepository.findByName(name);
//    }
}
