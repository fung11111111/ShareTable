package com.food.ShareTable.food.repository;

import com.food.ShareTable.food.entity.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends MongoRepository<Food,String> {
}
