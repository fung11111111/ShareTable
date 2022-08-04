package com.food.ShareTable.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "restaurant")
public class Restaurant {
    @Id
    private String id;
    private String name;
    private String address;


    public Restaurant() {
    }

    public Restaurant( String id,  String name,  String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Restaurant(String name,  String address) {
        this.name = name;
        this.address = address;
    }


}
