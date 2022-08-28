package com.food.ShareTable.unitTest.restaurant;

import com.food.ShareTable.restaurant.entity.Restaurant;
import com.food.ShareTable.restaurant.exception.RestaurantExistsException;
import com.food.ShareTable.restaurant.repository.RestaurantRepository;
import com.food.ShareTable.restaurant.service.RestaurantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
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
    public void should_return_Hello_restaurant_when_call_findById() {
        Optional<Restaurant> expected = Optional.of(new Restaurant("1", "Hello", "RM19, 2/F, GOOD MOKKOK, KL, HK"));
        Mockito.when(restaurantRepository.findById("1")).thenReturn(expected);

        Optional<Restaurant> actual = restaurantService.getRestaurantById("1");

        Assertions.assertEquals(expected.get().getName(), actual.get().getName());
        Assertions.assertEquals(expected.get().getAddress(), actual.get().getAddress());

    }

    @Test
    public void should_return_Hello_when_call_findByName() {
        List<Restaurant> expected = List.of(new Restaurant("Hello", "RM19, 2/F, GOOD MOKKOK, KL, HK"));
        Mockito.when(restaurantRepository.findByName("Hello")).thenReturn(expected);

        List<Restaurant> actual = restaurantService.getRestaurantByName("Hello");

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.get(0).getName(), actual.get(0).getName());
    }

    @Test
    public void should_return_Hell_when_call_insert() {
        Restaurant expected = new Restaurant("Hell", "RM19, 2/F, GOOD MOKKOK, KL, HK");
        Mockito.when(restaurantRepository.findByNameAndAddress("Hell", "RM19, 2/F, GOOD MOKKOK, KL, HK")).thenReturn(null);
        Mockito.when(restaurantRepository.insert(expected)).thenReturn(expected);

        Restaurant actual = restaurantService.insertRestaurant(expected);
        Assertions.assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void should_throw_exception_when_insert_existing_restaurant() {
        Mockito.when(restaurantRepository.findByNameAndAddress("Hello", "RM19, 2/F, GOOD MOKKOK, KL, HK")).thenReturn(List.of(new Restaurant("Hello", "RM19, 2/F, GOOD MOKKOK, KL, HK")));

        RestaurantExistsException exception = Assertions.assertThrows(RestaurantExistsException.class, () -> {
            restaurantService.insertRestaurant(new Restaurant("Hello", "RM19, 2/F, GOOD MOKKOK, KL, HK"));
        });

        Assertions.assertEquals("Restaurant is already existed.", exception.getLocalizedMessage());
    }

}
