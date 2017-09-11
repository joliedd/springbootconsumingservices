package ro.irina.pizza3.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ro.irina.pizza3.model.GeoPoint;
import ro.irina.pizza3.model.MenuItem;
import ro.irina.pizza3.model.Restaurant;
import ro.irina.pizza3.service.RestaurantService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
class RestaurantApiController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantApiController.class);

    @Autowired
    public RestaurantService restaurantService;

    @RequestMapping(value = "/api/v1/restaurants/all", method = RequestMethod.GET,  produces = "application/json")
    public  List<Restaurant> getAllRestaurants() throws Exception {

        return restaurantService.getAllRestaurants();
    }

    @RequestMapping(value = "/api/v1/restaurants/id/{rid}", method = RequestMethod.GET,  produces = "application/json")
    public  Restaurant getRestaurantById(@PathVariable Integer rid) throws Exception {

        return restaurantService.getRestaurantById(rid);
    }

    @RequestMapping(value = "/api/v1/restaurants/menu/id/{rid}", method = RequestMethod.GET,  produces = "application/json")
    public  List<MenuItem> getRestaurantMenuById(@PathVariable Integer rid) throws Exception {

        return restaurantService.getRestaurantMenuById(rid);
    }


    @RequestMapping(value = "/api/v1/restaurants/closest", method = RequestMethod.GET,  produces = "application/json")
    public Restaurant  getClosestRestaurant() throws Exception {
        /*  i can do geolocation too but in the next version of the app since it takes some time
		*/
        GeoPoint location = new GeoPoint(45.66,41.33);

        return restaurantService.getClosestRestaurant(location);

    }

}