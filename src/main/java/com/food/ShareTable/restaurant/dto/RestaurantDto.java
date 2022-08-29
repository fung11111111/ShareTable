package com.food.ShareTable.restaurant.dto;

import com.food.ShareTable.food.entity.Food;
import com.food.ShareTable.table.entity.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
    private String id;
    private String name;
    private String address;
    private List<Food> foodList = new ArrayList<>();
    private List<Table> tables = new ArrayList<>();

    public RestaurantDto(String name, String address, List<Food> foodList, List<Table> tables) {
        this.name = name;
        this.address = address;
        this.foodList = foodList;
        this.tables = tables;
    }
}
