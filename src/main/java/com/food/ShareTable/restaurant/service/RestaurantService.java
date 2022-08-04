package com.food.ShareTable.restaurant.service;

import com.food.ShareTable.restaurant.entity.Restaurant;
import com.food.ShareTable.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    public List<Restaurant> getAllRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId("obj1234");
        restaurant.setName("Happy Gathering");
        restaurant.setAddress("RM E, 2/F, Moko Building, Mok Kok, KL");
        return List.of(restaurant);
    }

    public Restaurant insertRestaurant(Restaurant restaurant) {
        return restaurantRepository.insert(restaurant);
    }

    public List<Restaurant> getRestaurantByName(String name) {
        return restaurantRepository.findByName(name);
    }

    public Restaurant getRestaurantById(String id) {
        return restaurantRepository.findById(id).get();
    }
}
