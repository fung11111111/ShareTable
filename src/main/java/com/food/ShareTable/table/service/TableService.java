package com.food.ShareTable.table.service;

import com.food.ShareTable.Common.CommonConstant;
import com.food.ShareTable.table.entity.Table;
import com.food.ShareTable.table.repository.TableRepository;
import org.apache.logging.slf4j.Log4jLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TableService {

    private final TableRepository tableRepository;


    @Autowired
    @Qualifier(CommonConstant.debugLogger)
    private Log4jLogger logger;

    @Autowired
    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public CompletableFuture<List<Table>> getTableByRestaurantIdAsync(String id) {
        return CompletableFuture.supplyAsync(() -> {
            logger.debug("getTableByRestaurantIdAsync() - {}", Thread.currentThread().getName());
            return tableRepository.findByRestaurantId(id);
        });
    }
}
