package com.food.ShareTable.restaurant.dto;

import com.food.ShareTable.food.entity.Food;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RestaurantDto {
    private String id;
    private String name;
    private String address;
    private List<Food> foodList = new ArrayList<>();

    public RestaurantDto() {
    }

    public RestaurantDto(String name, String address, List<Food> foodList) {
        this.name = name;
        this.address = address;
        this.foodList = foodList;
    }

    public RestaurantDto(String id, String name, String address, List<Food> foodList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.foodList = foodList;
    }
}
