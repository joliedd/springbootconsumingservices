package ro.irina.pizza3.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.irina.pizza3.dao.RestaurantDao;
import ro.irina.pizza3.model.GeoPoint;
import ro.irina.pizza3.model.MenuItem;
import ro.irina.pizza3.model.Restaurant;
import ro.irina.pizza3.utils.RestaurantUtils;

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
        if(location == null){
            throw new IllegalArgumentException(" GeoPoint id is null ");
        }

        List<Restaurant> restaurants = null;
        try {
            restaurants = this.getAllRestaurants();
        } catch (Exception e) {
            throw new Exception("Back end service response not successfull for getAllRestaurants");
        }
        double max = 999999999;
        Restaurant closestRestaurant=null;
        for (Restaurant restaurant:restaurants){
            double distance = RestaurantUtils.calculateDistance(location.getLatitude(),location.getLongitude(),restaurant.getLatitude(),restaurant.getLongitude());
            if(distance < max){
                max  = distance;
                closestRestaurant = restaurant;
            }
        }

        return closestRestaurant;
    }
}
