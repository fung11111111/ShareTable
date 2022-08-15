package com.food.ShareTable.food.controller;

import com.food.ShareTable.food.entity.Food;
import com.food.ShareTable.food.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/food")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }


    @PostMapping
    public Food create(@RequestBody Food food) {
        return foodService.createFood(food);
    }

}
