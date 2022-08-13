package com.food.ShareTable.restaurant.service;

import com.food.ShareTable.restaurant.entity.Restaurant;
import com.food.ShareTable.restaurant.exception.RestaurantExistsException;
import com.food.ShareTable.restaurant.repository.RestaurantRepository;
import org.apache.logging.slf4j.Log4jLogger;
import org.apache.logging.slf4j.Log4jLoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;


    private Log4jLogger logger = (Log4jLogger) LoggerFactory.getLogger("com.sharetable.app");

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    public Restaurant insertRestaurant(Restaurant restaurant) throws RestaurantExistsException {
        List<Restaurant> existingRestaurants = restaurantRepository.findByNameAndAddress(restaurant.getName(), restaurant.getAddress());
        logger.debug("Restaurant Service insertRestaurant name: {} and address: {}",restaurant.getName(), restaurant.getAddress());
        if (existingRestaurants != null && !existingRestaurants.isEmpty()) {
            throw new RestaurantExistsException();
        }
        return restaurantRepository.insert(restaurant);
    }

    public List<Restaurant> getRestaurantByName(String name) {
        return restaurantRepository.findByName(name);
    }

    public Restaurant getRestaurantById(String id) {
        return restaurantRepository.findById(id).get();
    }
}
