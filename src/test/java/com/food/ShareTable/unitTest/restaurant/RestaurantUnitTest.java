package com.food.ShareTable.unitTest.restaurant;

import com.food.ShareTable.restaurant.entity.Restaurant;
import com.food.ShareTable.restaurant.repository.RestaurantRepository;
import com.food.ShareTable.restaurant.service.RestaurantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RestaurantUnitTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    @Test
    public void should_return_restaurants_when_call_getAllRestaurant() {
        List<Restaurant> expected = List.of(new Restaurant("Hello", "RM19, 2/F, GOOD MOKKOK, KL, HK"));
        Mockito.when(restaurantRepository.findAll()).thenReturn(expected);

        List<Restaurant> actual = restaurantService.getAllRestaurant();

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.size(), actual.size());

    }

    @Test
    public void should_return_Hello_restaurant_when_call_findById(){
        Optional<Restaurant> expected = Optional.of(new Restaurant("1","Hello", "RM19, 2/F, GOOD MOKKOK, KL, HK"));
        Mockito.when(restaurantRepository.findById("1")).thenReturn(expected);

        Restaurant actual = restaurantService.getRestaurantById("1");

        Assertions.assertEquals(expected.get().getName(), actual.getName());
        Assertions.assertEquals(expected.get().getAddress(), actual.getAddress());

    }
}
