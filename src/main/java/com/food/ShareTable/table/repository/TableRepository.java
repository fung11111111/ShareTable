package com.food.ShareTable.table.repository;

import com.food.ShareTable.table.entity.Table;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends MongoRepository<Table,String> {
}
