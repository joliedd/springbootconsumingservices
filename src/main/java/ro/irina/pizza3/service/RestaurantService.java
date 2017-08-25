package ro.irina.pizza3.service;


import com.maxmind.geoip.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.irina.pizza3.dao.RestaurantDao;
import ro.irina.pizza3.model.GeoPoint;
import ro.irina.pizza3.model.Restaurant;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantDao restaurantDao;

    public List<Restaurant> getAllRestaurants(){
        return restaurantDao.getAllRestaurants();

    }

    public Restaurant getRestaurantById(Integer rid){
        return restaurantDao.getRestaurantById(rid);

    }

    public Restaurant getClosestRestaurant(GeoPoint location){
        return restaurantDao.getClosestRestaurant(location);

    }
}
