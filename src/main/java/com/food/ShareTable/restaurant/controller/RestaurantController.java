package com.food.ShareTable.restaurant.controller;

import com.food.ShareTable.restaurant.entity.Restaurant;
import com.food.ShareTable.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Restaurant> getRestaurants() {
        return restaurantService.getAllRestaurant();
    }

    @PostMapping
    public Restaurant create() {
        return restaurantService.insertRestaurant(new Restaurant("hello", "world"));
    }

//    @GetMapping("/{name}")
//    public List<Restaurant> getRestaurantsByName(@PathVariable String name) {
//        return restaurantService.getRestaurantByName(name);
//    }

    //cannot exist more than one get mapping path
    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable String id){
        System.out.println(id);
        return restaurantService.getRestaurantById(id);
    }

    //may use id for pathvariable, others for requestparam


}
