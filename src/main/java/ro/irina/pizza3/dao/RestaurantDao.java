package ro.irina.pizza3.dao;

import ro.irina.pizza3.model.GeoPoint;
import ro.irina.pizza3.model.MenuItem;
import ro.irina.pizza3.model.Restaurant;

import java.util.List;

public interface RestaurantDao {

    public List<Restaurant> getAllRestaurants() throws Exception;
    public Restaurant getRestaurantById(Integer rid) throws Exception;
    public List<MenuItem> getRestaurantMenuById(Integer rid) throws Exception;
    public Restaurant getClosestRestaurant(GeoPoint location) throws Exception;
}
