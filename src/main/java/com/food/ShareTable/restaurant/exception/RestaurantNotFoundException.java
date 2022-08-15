package com.food.ShareTable.restaurant.exception;

public class RestaurantNotFoundException extends RuntimeException{
    public RestaurantNotFoundException() {
        super("Restaurant Not Found.");
    }
}
