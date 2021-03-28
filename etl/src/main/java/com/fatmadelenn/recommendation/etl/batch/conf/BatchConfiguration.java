package com.fatmadelenn.recommendation.etl.batch.conf;

import com.fatmadelenn.recommendation.etl.batch.OrderItemsRowMapper;
import com.fatmadelenn.recommendation.etl.batch.ProductsDetailProcessor;
import com.fatmadelenn.recommendation.etl.dto.OrderItemsDetails;
import com.fatmadelenn.recommendation.etl.entity.ProductsDetail;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobRegistry jobRegistry;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public Job importUserJob() {
        return jobs.get("databaseToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return steps.get("databaseToDatabaseStep")
                .<OrderItemsDetails, ProductsDetail>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemStreamReader<OrderItemsDetails> reader() {
        try (Connection dbConnection = dataSource.getConnection()) {
            try (Statement statement = dbConnection.createStatement()) {
                statement.execute("create table if not exists products_detail (id int not null, product_id varchar(50), total_user int, primary key (id))");//if exist
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        JdbcCursorItemReader<OrderItemsDetails> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("SELECT t1.order_id, t3.user_id, t2.product_id, t2.category_id, t1.quantity " +
                "from public.order_items as t1 " +
                "INNER JOIN public.products as t2 ON t2.product_id = t1.product_id " +
                "INNER JOIN public.orders t3 ON t3.order_id = t1.order_id");
        reader.setRowMapper(new OrderItemsRowMapper());
        return reader;

    }


    @Bean
    public ItemProcessor<OrderItemsDetails, ProductsDetail> processor() {
        return new ProductsDetailProcessor();
    }

    @Bean
    public ItemWriter<ProductsDetail> writer() {
        JdbcBatchItemWriter<ProductsDetail> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("insert into products_detail (id, product_id, total_user) values (nextval('hibernate_sequence'), :productId, :totalUser)");
        writer.setDataSource(dataSource);
        return writer;
    }

}
