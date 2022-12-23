package com.demo.kafka.consumer;

import com.demo.kafka.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {Constants.TOPIC})
    public void onMessage(ConsumerRecord<?, ?> record) {
        log.info("Received from partition: {}, message: {}", record.partition(), record.value());
    }
}
