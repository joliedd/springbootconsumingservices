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
class RestaurantController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    public RestaurantService restaurantService;

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {

        List<Restaurant> restaurants = null;
        try {
            restaurants = restaurantService.getAllRestaurants();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,Object> params = new HashMap<>();
        params.put("restaurants",restaurants);

       return new ModelAndView("index",params);
    }

    @RequestMapping(value = "/restaurants/id/{rid}", method = RequestMethod.GET)
    public ModelAndView restaurantById(@PathVariable Integer rid) {

        log.info("rid:" +rid);
        Restaurant restaurant = null;
        List<MenuItem> menu = null;
        try {
            restaurant = restaurantService.getRestaurantById(rid);
            menu = restaurantService.getRestaurantMenuById(rid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String,Object> params = new HashMap<>();
        params.put("restaurant",restaurant);
        params.put("menu",menu);

        return new ModelAndView("restaurant",params);
    }

    @RequestMapping(value = "/restaurants/closest", method = RequestMethod.GET)
    public RedirectView restaurantClosest() {

        GeoPoint location = new GeoPoint(45,41);
        Restaurant restaurant = null;
        try {
            restaurant = restaurantService.getClosestRestaurant(location);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RedirectView("/restaurants/id/"+restaurant.getId());
    }


}