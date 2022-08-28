package com.food.ShareTable.table.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Table {
    @Id
    private String id;
    private String tableNumber;
    private int ttlSeats;
    private int unavailableSeats;
    private String restaurantId;

    public Table(String tableNumber, int ttlSeats, int unavailableSeats, String restaurantId) {
        this.tableNumber = tableNumber;
        this.ttlSeats = ttlSeats;
        this.unavailableSeats = unavailableSeats;
        this.restaurantId = restaurantId;
    }
}
