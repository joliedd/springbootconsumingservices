package ro.irina.pizza3.api;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ro.irina.pizza3.service.RestaurantService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @MockBean
    private RestaurantService restaurantService;

    @Autowired
    private MockMvc mockMvc;

    /* i can do more tests in next version */
    @Test
    public void indexTest() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("restaurants"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertNotNull(result.getModelAndView().getModel().get("restaurants"));
    }


}
