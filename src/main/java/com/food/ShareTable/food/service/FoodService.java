package com.food.ShareTable.food.service;

import com.food.ShareTable.Common.CommonConstant;
import com.food.ShareTable.food.entity.Food;
import com.food.ShareTable.food.repository.FoodRepository;
import org.apache.logging.slf4j.Log4jLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    @Qualifier(CommonConstant.debugLogger)
    private Log4jLogger logger;


    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }


    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    public CompletableFuture<List<Food>> getAllFoodAsync(){
        return CompletableFuture.supplyAsync(() -> {
            logger.debug("getAllFoodAsync() Current threads - " + Thread.currentThread().getName());
            return foodRepository.findAll();
        });
    }
}
