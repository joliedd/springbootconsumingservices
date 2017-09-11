package ro.irina.pizza3.api;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ro.irina.pizza3.service.RestaurantService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantApiControllerTest {

    @MockBean
    private RestaurantService restaurantService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllRestaurantsTest() throws Exception {

        this.mockMvc.perform(get("/api/v1/restaurants/all")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
}

