package com.fatmadelenn.recommendation.viewproducer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatmadelenn.recommendation.viewproducer.dto.ProductViews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Component
public class ProductMessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(ProductMessageProducer.class);

    @Autowired
    private KafkaTemplate<String, ProductViews> kafkaTemplate;

    @Value("${recommendation.kafka.topic}")
    private String kafkaTopic;

    @Bean
    public void publishToQueue() throws IOException, InterruptedException {
        UUID messageUUID = UUID.randomUUID(); //Kuyrukta ilgili mesaja key setledim.
        List<String> messages = Files.readAllLines(Paths.get("product-views.json"), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        for(String message : messages){
            ProductViews product = objectMapper.readValue(message, ProductViews.class);
            ListenableFuture<SendResult<String, ProductViews>> future =  kafkaTemplate.send(kafkaTopic, messageUUID.toString(), product);
            Thread.sleep(1000);
            future.addCallback(new ListenableFutureCallback<SendResult<String, ProductViews>>() {

                @Override
                public void onSuccess(SendResult<String, ProductViews> result) {
                    logger.info("Sent message=[{}] with offset=[{}}]", message, result.getRecordMetadata().offset());
                }

                @Override
                public void onFailure(Throwable ex) {
                    logger.error("Unable to send message=[{}] due to : {}", message, ex.getMessage());
                }
            });
        }
    }
}
