package ro.irina.pizza3.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.irina.pizza3.dao.RestaurantDao;
import ro.irina.pizza3.model.GeoPoint;
import ro.irina.pizza3.model.MenuItem;
import ro.irina.pizza3.model.Restaurant;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantDao restaurantDao;

    public List<Restaurant> getAllRestaurants() throws Exception {
        return restaurantDao.getAllRestaurants();
    }

    public Restaurant getRestaurantById(Integer rid) throws Exception {
        return restaurantDao.getRestaurantById(rid);
    }

    public List<MenuItem> getRestaurantMenuById(Integer rid) throws Exception {
        return restaurantDao.getRestaurantMenuById(rid);
    }

    public Restaurant getClosestRestaurant(GeoPoint location) throws Exception {
        return restaurantDao.getClosestRestaurant(location);
    }
}
