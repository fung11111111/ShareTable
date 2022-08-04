package com.food.ShareTable.restaurant.controller;

import com.food.ShareTable.restaurant.entity.Restaurant;
import com.food.ShareTable.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @GetMapping
    public List<Restaurant> getRestaurants(){
        return restaurantService.getAllRestaurant();
    }

    @PostMapping
    public Restaurant create(){
        return restaurantService.insertRestaurant(new Restaurant("hello", "world"));
    }
}
