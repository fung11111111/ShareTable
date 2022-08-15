package com.food.ShareTable.food.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "food")
public class Food {

    @Id
    private String id;
    private String name;
    private Integer price;
    private String type;
    private Boolean isRecommended;

    public Food() {
    }

    public Food(String name, Integer price, String type, Boolean isRecommended) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.isRecommended = isRecommended;
    }

    public Food(String id, String name, Integer price, String type, Boolean isRecommended) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.isRecommended = isRecommended;
    }
}
