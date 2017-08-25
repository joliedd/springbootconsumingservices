package ro.irina.pizza3.utils;

import ro.irina.pizza3.model.Restaurant;

import java.util.List;

public class RestaurantUtils {

    public static double calculateDistance(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
    }

    public static Restaurant containsName(final List<Restaurant> list, final Integer id){
        return list.stream().filter(o -> o.getId().equals(id)).findFirst().get();
    }
}
