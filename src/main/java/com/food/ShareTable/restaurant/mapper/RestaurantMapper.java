package com.food.ShareTable.restaurant.mapper;

import com.food.ShareTable.restaurant.dto.RestaurantDto;
import com.food.ShareTable.restaurant.entity.Restaurant;
import org.springframework.stereotype.Service;

@Service
public class RestaurantMapper {
    public Restaurant toEntity(RestaurantDto restaurantDto) {
        return new Restaurant(restaurantDto.getId(), restaurantDto.getName(), restaurantDto.getAddress());
    }

    public RestaurantDto toDto(Restaurant restaurant) {
        return new RestaurantDto(restaurant.getId(), restaurant.getName(), restaurant.getAddress());
    }
}
