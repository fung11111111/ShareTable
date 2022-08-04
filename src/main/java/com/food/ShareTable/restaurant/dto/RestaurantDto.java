package com.food.ShareTable.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDto {
    private String id;
    private String name;
    private String address;

    public RestaurantDto() {
    }

    public RestaurantDto(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public RestaurantDto(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
