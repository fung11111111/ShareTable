package com.food.ShareTable.restaurant.controller;

import com.food.ShareTable.restaurant.dto.RestaurantDto;
import com.food.ShareTable.restaurant.entity.Restaurant;
import com.food.ShareTable.restaurant.exception.RestaurantExistsException;
import com.food.ShareTable.restaurant.mapper.RestaurantMapper;
import com.food.ShareTable.restaurant.service.RestaurantService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;


    @Autowired
    public RestaurantController(RestaurantService restaurantService, RestaurantMapper restaurantMapper) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
    }

    @GetMapping
    public List<RestaurantDto> getRestaurants() {
        return restaurantService.getAllRestaurant().stream()
                .map(restaurantMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public RestaurantDto create(@NonNull @RequestBody RestaurantDto restaurantDto) throws RestaurantExistsException {
        return restaurantMapper.toDto(restaurantService.insertRestaurant(restaurantMapper.toEntity(restaurantDto)));
    }

    //cannot exist more than one get mapping path
    //may use id for pathvariable, others for requestparam
    @GetMapping("/{id}")
    public RestaurantDto getRestaurantById(@PathVariable String id) {
        return restaurantMapper.toDto(restaurantService.getRestaurantById(id));
    }

    @RequestMapping("/info")
    @ResponseBody
    public List<RestaurantDto> getRestaurantsByName(@RequestParam(name = "name") String name) {
        return restaurantService.getRestaurantByName(name).stream()
                .map(restaurantMapper::toDto)
                .collect(Collectors.toList());
    }


}
