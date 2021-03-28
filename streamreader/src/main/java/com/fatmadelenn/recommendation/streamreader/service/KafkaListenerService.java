package com.fatmadelenn.recommendation.streamreader.service;

import com.fatmadelenn.recommendation.streamreader.entity.ProductViews;
import com.fatmadelenn.recommendation.streamreader.repository.ProductViewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaListenerService.class);

    @Autowired
    private ProductViewsRepository productViewsRepository;

    @KafkaListener(topics = "${recommendation.kafka.topic}", groupId = "${recommendation.kafka.group.id}")
    public void messageListener(@Payload ProductViews kafkaMessage) {
        logger.info("Consumed message : " + kafkaMessage);
        productViewsRepository.save(kafkaMessage);
    }
}
