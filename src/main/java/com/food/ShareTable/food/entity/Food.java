package com.food.ShareTable.food.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "food")
public class Food {

    @Id
    private String id;
    private String name;
    private Integer price;
    private String type;
    private Boolean isRecommended;
    private String restaurantId;


    public Food(String name, Integer price, String type, Boolean isRecommended, String restaurantId) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.isRecommended = isRecommended;
        this.restaurantId = restaurantId;
    }
}
