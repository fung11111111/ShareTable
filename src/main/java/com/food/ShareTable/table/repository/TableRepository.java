package com.food.ShareTable.table.repository;

import com.food.ShareTable.table.entity.Table;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends MongoRepository<Table,String> {

    List<Table> findByRestaurantId(String restaurantId);
}
