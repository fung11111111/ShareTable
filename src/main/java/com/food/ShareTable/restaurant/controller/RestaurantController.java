package com.food.ShareTable.restaurant.controller;

import com.food.ShareTable.food.service.FoodService;
import com.food.ShareTable.restaurant.dto.RestaurantDto;
import com.food.ShareTable.restaurant.exception.RestaurantExistsException;
import com.food.ShareTable.restaurant.mapper.RestaurantMapper;
import com.food.ShareTable.restaurant.service.RestaurantService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;
    private final FoodService foodService;


    @Autowired
    public RestaurantController(RestaurantService restaurantService, RestaurantMapper restaurantMapper, FoodService foodService) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
        this.foodService = foodService;
    }

    @GetMapping
    public List<RestaurantDto> getRestaurants() {

        return restaurantService.getAllRestaurant().parallelStream()
                .map(restaurant -> {
                    return restaurantMapper.toDto(restaurant, new ArrayList<>());
                })
                .collect(Collectors.toList());
    }

    @PostMapping
    public RestaurantDto create(@NonNull @RequestBody RestaurantDto restaurantDto) throws RestaurantExistsException {
        return restaurantMapper.toDto(restaurantService.insertRestaurant(restaurantMapper.toEntity(restaurantDto)), new ArrayList<>());
    }

    //cannot exist more than one get mapping path
    //may use id for pathvariable, others for requestparam
    @GetMapping("/{id}")
    public RestaurantDto getRestaurantById(@PathVariable String id) {
        return restaurantMapper.toDto(restaurantService.getRestaurantById(id), new ArrayList<>());
    }

    @RequestMapping("/info")
    @ResponseBody
    public List<RestaurantDto> getRestaurantsByName(@RequestParam(name = "name") String name) {
        return restaurantService.getRestaurantByName(name).stream()
                .map(restaurant -> {
                    return restaurantMapper.toDto(restaurant, new ArrayList<>());
                })
                .collect(Collectors.toList());
    }

    @RequestMapping("/withFood")
    @ResponseBody
    public RestaurantDto getRestaurantWithFoodById(@RequestParam(name = "id") String id) {
        CompletableFuture<RestaurantDto> completableFuture = restaurantService.getRestaurantByIdAsync(id).thenCombine(foodService.getAllFoodAsync(), (res, foodList) -> {
            return restaurantMapper.toDto(res, foodList);
        });

        return completableFuture.join();
    }

}
