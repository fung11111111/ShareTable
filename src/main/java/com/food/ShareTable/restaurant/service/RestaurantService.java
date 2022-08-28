package com.food.ShareTable.restaurant.service;

import com.food.ShareTable.Common.CommonConstant;
import com.food.ShareTable.restaurant.entity.Restaurant;
import com.food.ShareTable.restaurant.exception.RestaurantExistsException;
import com.food.ShareTable.restaurant.exception.RestaurantNotFoundException;
import com.food.ShareTable.restaurant.repository.RestaurantRepository;
import org.apache.logging.slf4j.Log4jLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    @Qualifier(CommonConstant.debugLogger)
    private Log4jLogger logger;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    public Restaurant insertRestaurant(Restaurant restaurant) throws RestaurantExistsException {
        List<Restaurant> existingRestaurants = restaurantRepository.findByNameAndAddress(restaurant.getName(), restaurant.getAddress());
        logger.debug("Restaurant Service insertRestaurant name: {} and address: {}", restaurant.getName(), restaurant.getAddress());
        if (existingRestaurants != null && !existingRestaurants.isEmpty()) {
            throw new RestaurantExistsException();
        }
        return restaurantRepository.insert(restaurant);
    }

    public List<Restaurant> getRestaurantByName(String name) {
        return restaurantRepository.findByName(name);
    }

    public Optional<Restaurant> getRestaurantById(String id) {
        logger.debug("getRestaurantById() Current threads - {}", Thread.currentThread().getName());
        return restaurantRepository.findById(id);
    }

    public CompletableFuture<Optional<Restaurant>> getRestaurantByIdAsync(String id) {
        return CompletableFuture.supplyAsync(() -> {
            logger.debug("getRestaurantByIdAsync() Current threads - {}", Thread.currentThread().getName());
            return restaurantRepository.findById(id);
        });
    }

    public CompletableFuture<Restaurant> updateRestaurantByIdAsync(String id, Restaurant restaurant) {
        return CompletableFuture.supplyAsync(() -> {
            if (!restaurantRepository.existsById(id)) throw new RestaurantNotFoundException();

            logger.debug("updateRestaurantByIdAsync() Current threads - {}", Thread.currentThread().getName());
            return restaurantRepository.save(restaurant);
        });
    }
}
