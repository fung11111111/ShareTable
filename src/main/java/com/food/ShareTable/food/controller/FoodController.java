package com.food.ShareTable.food.controller;

import com.food.ShareTable.food.entity.Food;
import com.food.ShareTable.food.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping("/withRestaurant")
    @ResponseBody
    public List<Food> getFoodByRestaurantId(@RequestParam(name = "restaurantId") String restaurantId) {
        System.out.println("getFoodByRestaurantId - " + restaurantId);
        return foodService.getFoodsByRestaurantIdAsync(restaurantId).join();
    }

    @GetMapping
    public List<Food> getAllFood() {
        return foodService.getAllFood();
    }

}
