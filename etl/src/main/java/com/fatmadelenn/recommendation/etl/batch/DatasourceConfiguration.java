package com.fatmadelenn.recommendation.etl.batch;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfiguration {

    @Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://127.0.0.1:5432/data-db")
                .username("postgres")
                .password("123456")
                .build();
    }
}
