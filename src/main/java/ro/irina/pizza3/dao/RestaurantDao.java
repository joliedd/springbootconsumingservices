package ro.irina.pizza3.dao;

import ro.irina.pizza3.model.GeoPoint;
import ro.irina.pizza3.model.Restaurant;

import java.awt.*;
import java.util.List;

public interface RestaurantDao {

    public List<Restaurant> getAllRestaurants();
    public Restaurant getRestaurantById(Integer rid);
    public Restaurant getClosestRestaurant(GeoPoint location);
}
