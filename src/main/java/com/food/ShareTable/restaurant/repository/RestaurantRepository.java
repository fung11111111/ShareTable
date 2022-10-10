package com.food.ShareTable.restaurant.repository;

import com.food.ShareTable.restaurant.entity.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    List<Restaurant> findByName(String name);

    List<Restaurant> findByNameAndAddress(String name, String address);

    @Query("{'name' : ?0, 'address: ?1'}")
    List<Restaurant> findByNameAndAddressQuery(String name, String address);
}
