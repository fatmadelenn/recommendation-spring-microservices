package com.fatmadelenn.recommendation.ecommerce;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatmadelenn.recommendation.ecommerce.dto.DetailType;
import com.fatmadelenn.recommendation.ecommerce.dto.ProductResponse;
import com.fatmadelenn.recommendation.ecommerce.dto.Properties;
import com.fatmadelenn.recommendation.ecommerce.entity.ProductViews;
import com.fatmadelenn.recommendation.ecommerce.repository.ProductViewsRepository;
import com.fatmadelenn.recommendation.ecommerce.repository.ProductsDetailRepository;
import com.fatmadelenn.recommendation.ecommerce.repository.ProductsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductViewsControllerTest {

    private static final String API_HISTORY = "/api/history";
    private static final String API_BEST_SELLER = "/api/best-seller";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    public ProductViewsRepository productViewsRepository;

    @Autowired
    public ProductsDetailRepository productsDetailRepository;

    @Autowired
    public ProductsRepository productsRepository;

    @Autowired
    public ObjectMapper objectMapper;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void getLastHistory() throws Exception {
        String userId = "user-67";
        long productCount = productViewsRepository.findTop10ByUseridOrderByTimestampDesc(userId)
                .stream().map(ProductViews::getProperties).map(Properties::getProductid).distinct().count();
        MvcResult mvcResult = mockMvc.perform(
                get(API_HISTORY + "?userId=" + userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();
        ProductResponse product = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ProductResponse.class);
        int count = productCount <= 5 ? 0 : (int) productCount;
        assertThat(product.getProducts().size(), is(equalTo(count)));
        assertThat(product.getUserId(), is(equalTo(userId)));
    }


    @Test
    public void deleteHistory() throws Exception {
        String userId = "user-5";
        String productId = "product-3";

        MvcResult mvcResult = mockMvc.perform(
                delete(API_HISTORY + "/delete?userId=" + userId + "&productId=" + productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        List<ProductViews> productViews = productViewsRepository.findByUseridAndProperties(userId, new Properties(productId));
        if (productViews.isEmpty()) {
            assertThat(mvcResult.getResponse().getStatus(), is(equalTo(400)));
        } else {
            String result = String.format("Successfully deleted product from their history, product: %s", productViews);
            assertThat(mvcResult.getResponse().getContentAsString(), is(equalTo(result)));
        }
    }

    @Test
    public void getBestSeller() throws Exception {
        String userId = "user-890";
        MvcResult mvcResult = mockMvc.perform(
                get(API_BEST_SELLER + "?userId=" + userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        List<ProductViews> history = productViewsRepository.findByUserid(userId);
        DetailType type = !CollectionUtils.isEmpty(history) ? DetailType.PERSONALIZED : DetailType.NON_PERSONALIZED;
        int totalProduct = CollectionUtils.isEmpty(history) ? 10 : 30;

        ProductResponse product = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ProductResponse.class);
        //assertThat(product.getProducts().size(), is(equalTo(totalProduct)));
        assertThat(product.getUserId(), is(equalTo(userId)));
        assertThat(product.getType(), is(equalTo(type)));
    }
}
