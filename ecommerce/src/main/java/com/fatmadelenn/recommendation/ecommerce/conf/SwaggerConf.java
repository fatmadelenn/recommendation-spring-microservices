package com.fatmadelenn.recommendation.ecommerce.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConf {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fatmadelenn.recommendation.ecommerce.controller"))
                .paths(PathSelectors.ant("/api/**"))
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Recommendation API",
                "REST API documentation",
                "1.0",
                null,
                new Contact("Fatma Delen", "https://github.com/fatmadelenn", "fatmadelen8@gmail.com"),
                null, null, Collections.emptyList());
    }
}
