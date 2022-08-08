package com.food.ShareTable.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class RestaurantExistsException extends RuntimeException {

    public RestaurantExistsException() {
        super("Restaurant is already existed.");
    }
}
