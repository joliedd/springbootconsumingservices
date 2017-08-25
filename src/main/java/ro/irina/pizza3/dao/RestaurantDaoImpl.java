package ro.irina.pizza3.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import ro.irina.pizza3.model.GeoPoint;
import ro.irina.pizza3.model.MenuItem;
import ro.irina.pizza3.model.Restaurant;
import ro.irina.pizza3.utils.RestaurantUtils;

import java.util.Arrays;
import java.util.List;


@Repository("restaurantDao")
public class RestaurantDaoImpl implements RestaurantDao {

    private static final Logger log = LoggerFactory.getLogger(RestaurantDaoImpl.class);

    @Override
    @Cacheable("restaurants")
    public List<Restaurant> getAllRestaurants() throws Exception {

        String uri = "https://private-f42b1-apegrouppizzaappapi.apiary-mock.com/restaurants/";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Restaurant[]> responseEntity = restTemplate.getForEntity(uri, Restaurant[].class);
        log.info("responseEntity: "+ responseEntity.toString());

        Restaurant[] restaurants = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();

        log.info("restaurants list statusCode: "+ statusCode);

        if (!statusCode.is2xxSuccessful()) {
            throw new Exception("Back end service response not successfull for getAllRestaurants");
        }

        log.info("restaurants list: "+ Arrays.asList(restaurants));

        return Arrays.asList(restaurants);


    }

    @Override
    @Cacheable("restaurant")
    public Restaurant getRestaurantById(Integer rid) throws Exception {

        if(rid == null){
            throw new IllegalArgumentException(" Restaurant id is null ");
        }

        List<Restaurant> restaurants = null;

        try {
            restaurants = this.getAllRestaurants();
        } catch (Exception e) {
            throw new Exception("Back end service response not successfull for getAllRestaurants");
        }

        Restaurant restaurant = RestaurantUtils.containsName(restaurants, rid);

        if (restaurant == null ) {
            String uri = "https://private-f42b1-apegrouppizzaappapi.apiary-mock.com/restaurants/" + rid;
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Restaurant> responseEntity = restTemplate.getForEntity(uri, Restaurant.class);

            log.info("responseEntity: " + responseEntity.toString());

            restaurant = responseEntity.getBody();
            HttpStatus statusCode = responseEntity.getStatusCode();

            log.info("restaurantbyid statusCode: " + statusCode);
            log.info("restaurantbyid: " + restaurant.toString());

            if (!statusCode.is2xxSuccessful()) {
                throw new Exception("Back end service response not successfull for getRestaurantById");
            }
        }
        return restaurant;
    }

    @Override
    @Cacheable("menu")
    public List<MenuItem> getRestaurantMenuById(Integer rid) throws Exception {

        if(rid == null){
            throw new IllegalArgumentException(" Restaurant id is null ");
        }

        String uri = "https://private-f42b1-apegrouppizzaappapi.apiary-mock.com/restaurants/"+rid+"/menu?category=Pizza&orderBy=rank";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MenuItem[]> responseEntity = restTemplate.getForEntity(uri, MenuItem[].class);
        log.info("responseEntity: "+ responseEntity.toString());

        MenuItem[] menuItems = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();

        log.info("restaurantbyidmenu statusCode: "+ statusCode);
        log.info("restaurantbyidmenu: "+ menuItems);

        if (!statusCode.is2xxSuccessful()) {
            throw new Exception("Back end service response not successfull for getRestaurantMenuById");

        }

        return Arrays.asList(menuItems);
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
