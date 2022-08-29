package com.food.ShareTable.restaurant.mapper;

import com.food.ShareTable.food.entity.Food;
import com.food.ShareTable.restaurant.dto.RestaurantDto;
import com.food.ShareTable.restaurant.entity.Restaurant;
import com.food.ShareTable.table.entity.Table;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantMapper {
    public Restaurant toEntity(RestaurantDto restaurantDto) {
        return new Restaurant(restaurantDto.getId(), restaurantDto.getName(), restaurantDto.getAddress());
    }

    public RestaurantDto toDto(Restaurant restaurant, List<Food> foodList,List<Table> tables) {
        return new RestaurantDto(restaurant.getId(), restaurant.getName(), restaurant.getAddress(), foodList, tables);
    }
}
