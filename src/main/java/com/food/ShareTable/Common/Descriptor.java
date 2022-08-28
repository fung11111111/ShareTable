package com.food.ShareTable.Common;

import com.food.ShareTable.table.entity.Table;
import com.food.ShareTable.table.repository.TableRepository;
import org.springframework.stereotype.Service;

//to create fake data
@Service
public class Descriptor {

    private final TableRepository tableRepository;

    public Descriptor(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public void createTableByRestaurantId(String id) {
        int s = 20;

        for (int i = 0; i < s; i++) {
            if (i < 5) {
                tableRepository.insert(new Table(String.valueOf(i), 2, 0, id));
            } else if (i < 10) {
                tableRepository.insert(new Table(String.valueOf(i), 4, 0, id));
            } else {
                tableRepository.insert(new Table(String.valueOf(i), 8, 0, id));
            }
        }
    }
}
