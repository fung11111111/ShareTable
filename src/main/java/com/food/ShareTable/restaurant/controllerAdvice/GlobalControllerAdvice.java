package com.food.ShareTable.restaurant.controllerAdvice;

import com.food.ShareTable.restaurant.exception.RestaurantExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(RestaurantExistsException.class)
    public ResponseEntity<Object> handleRestaurantExists(RestaurantExistsException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.CONFLICT.name()), HttpStatus.CONFLICT);
    }
}
