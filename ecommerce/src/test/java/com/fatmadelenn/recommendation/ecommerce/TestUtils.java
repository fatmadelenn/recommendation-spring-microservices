package com.fatmadelenn.recommendation.ecommerce;

import com.fatmadelenn.recommendation.ecommerce.dto.Context;
import com.fatmadelenn.recommendation.ecommerce.dto.Properties;
import com.fatmadelenn.recommendation.ecommerce.dto.Source;
import com.fatmadelenn.recommendation.ecommerce.entity.ProductViews;

public class TestUtils {
    public static ProductViews createProductViews() {
        ProductViews productViews= new ProductViews();
        productViews.setId(1L);
        productViews.setUserid("user-5");
        productViews.setProperties(new Properties("product-5"));
        productViews.setContext(new Context(Source.DESKTOP));
        return productViews;
    }
}
