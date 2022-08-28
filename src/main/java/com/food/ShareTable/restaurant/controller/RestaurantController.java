package com.food.ShareTable.restaurant.controller;

import com.food.ShareTable.Common.CommonConstant;
import com.food.ShareTable.Common.Descriptor;
import com.food.ShareTable.food.constant.FoodConstant;
import com.food.ShareTable.food.entity.Food;
import com.food.ShareTable.food.service.FoodService;
import com.food.ShareTable.restaurant.dto.RestaurantDto;
import com.food.ShareTable.restaurant.entity.Restaurant;
import com.food.ShareTable.restaurant.exception.RestaurantExistsException;
import com.food.ShareTable.restaurant.exception.RestaurantNotFoundException;
import com.food.ShareTable.restaurant.mapper.RestaurantMapper;
import com.food.ShareTable.restaurant.service.RestaurantService;
import lombok.NonNull;
import org.apache.logging.slf4j.Log4jLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private Descriptor descriptor;

    @Autowired
    @Qualifier(CommonConstant.debugLogger)
    private Log4jLogger logger;


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
        List<Food> foodList = foodService.getAllFood();
        return restaurantService.getRestaurantById(id)
                .map(restaurant -> restaurantMapper.toDto(restaurant, foodList))
                .orElseThrow(RestaurantNotFoundException::new);

    }

    //e.g. localhost:8080/api/v1/restaurant/info?name="hello"
    @RequestMapping("/info")
    @ResponseBody
    public List<RestaurantDto> getRestaurantsByName(@RequestParam(name = "name") String name) {
        return restaurantService.getRestaurantByName(name).stream()
                .map(restaurant -> {
                    return restaurantMapper.toDto(restaurant, new ArrayList<>());
                })
                .collect(Collectors.toList());
    }

    //test thenApply with fist found restaurant
    @RequestMapping("/infoWithBreakfast")
    @ResponseBody
    public RestaurantDto getRestaurantsByNameWithBreakFast(@RequestParam(name = "name") String name) {

        return CompletableFuture.supplyAsync(() -> {
            return restaurantService.getRestaurantByName(name).stream().findFirst().orElseThrow(RestaurantNotFoundException::new);
        }).thenApply(restaurant -> {
            List<Food> lunchFoods = foodService.getFoodsByRestaurantId(restaurant.getId()).stream()
                    .filter(f -> f.getType().equals(FoodConstant.TYPE_BREAKFAST)).collect(Collectors.toList());
            return restaurantMapper.toDto(restaurant, lunchFoods);
        }).join();

    }


    @RequestMapping("/withFood")
    @ResponseBody
    public RestaurantDto getRestaurantWithFoodById(@RequestParam(name = "id") String id) {
        return restaurantService.getRestaurantByIdAsync(id).thenCombine(foodService.getFoodsByRestaurantIdAsync(id), (resOptional, foodList) -> {
            return resOptional.map(restaurant -> {
                return restaurantMapper.toDto(restaurant, foodList);
            }).orElseThrow(RestaurantNotFoundException::new);
        }).join();
    }

    @RequestMapping("/withLunch")
    @ResponseBody
    public RestaurantDto getRestaurantWithLunchMenu(@RequestParam(name = "id") String id) {
        return restaurantService.getRestaurantByIdAsync(id).thenCombine(foodService.getFoodsByRestaurantIdAsync(id), ((restaurantOptional, foods) -> {
            return restaurantOptional.map(restaurant -> {
                return restaurantMapper.toDto(restaurant, foods.stream().filter(f -> f.getType().equals(FoodConstant.TYPE_LUNCH)).collect(Collectors.toList()));
            }).orElseThrow(RestaurantNotFoundException::new);
        })).join();
    }

    @RequestMapping("/withDinner")
    @ResponseBody
    public RestaurantDto getRestaurantWithDinnerMenu(@RequestParam(name = "id") String id) {
        return restaurantService.getRestaurantByIdAsync(id).thenCombine(foodService.getFoodsByRestaurantIdAsync(id), ((restaurantOptional, foods) -> {
            return restaurantOptional.map(restaurant -> {
                return restaurantMapper.toDto(restaurant, foods.stream().filter(f -> f.getType().equals(FoodConstant.TYPE_DINNER)).collect(Collectors.toList()));
            }).orElseThrow(RestaurantNotFoundException::new);
        })).join();
    }

    @RequestMapping("/withBreakfast")
    @ResponseBody
    public RestaurantDto getRestaurantWithBreakfastMenu(@RequestParam(name = "id") String id) {
        return restaurantService.getRestaurantByIdAsync(id).thenCombine(foodService.getFoodsByRestaurantIdAsync(id), ((restaurantOptional, foods) -> {
            return restaurantOptional.map(restaurant -> {
                return restaurantMapper.toDto(restaurant, foods.stream().filter(f -> f.getType().equals(FoodConstant.TYPE_BREAKFAST)).collect(Collectors.toList()));
            }).orElseThrow(RestaurantNotFoundException::new);
        })).join();
    }

    @RequestMapping("/withTea")
    @ResponseBody
    public RestaurantDto getRestaurantWithTeaMenu(@RequestParam(name = "id") String id) {
        return restaurantService.getRestaurantByIdAsync(id).thenCombine(foodService.getFoodsByRestaurantIdAsync(id), ((restaurantOptional, foods) -> {
            return restaurantOptional.map(restaurant -> {
                return restaurantMapper.toDto(restaurant, foods.stream().filter(f -> f.getType().equals(FoodConstant.TYPE_TEA_TIME)).collect(Collectors.toList()));
            }).orElseThrow(RestaurantNotFoundException::new);
        })).join();
    }

    @RequestMapping("/withRecommended")
    @ResponseBody
    public RestaurantDto getRestaurantWithRcdMenu(@RequestParam(name = "id") String id) {
        return restaurantService.getRestaurantByIdAsync(id).thenCombine(foodService.getFoodsByRestaurantIdAsync(id), ((restaurantOptional, foods) -> {
            return restaurantOptional.map(restaurant -> {
                return restaurantMapper.toDto(restaurant, foods.stream().filter(Food::getIsRecommended).collect(Collectors.toList()));
            }).orElseThrow(RestaurantNotFoundException::new);
        })).join();
    }

    @PutMapping("/{id}")
    public RestaurantDto updateRestaurantWithId(@PathVariable String id, @NonNull @RequestBody RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantMapper.toEntity(restaurantDto);
        return restaurantService.updateRestaurantByIdAsync(id, restaurant)
                .thenApply(res -> {
                    return restaurantMapper.toDto(res, new ArrayList<>());
                }).join();
    }


}
