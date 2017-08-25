package ro.irina.pizza3.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ro.irina.pizza3.dao.RestaurantDao;
import ro.irina.pizza3.model.Restaurant;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantService.class)
public class RestaurantServiceTest {

    @MockBean
    private RestaurantDao restaurantDao;

    @Autowired
    private MockMvc mockMvc;

    /* i can do more tests in next version */
    @Test
    public void getRestaurantByIdTest() throws Exception {

        Restaurant mockRestaurant = new Restaurant("Pizza Heaven", "Kungsgatan 1", "111 43 Stockholm",59.336078,18.071807 );
        Mockito.when(restaurantDao.getRestaurantById(1)).thenReturn(mockRestaurant);

    }


}
