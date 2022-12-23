package com.demo.kafka.producer;

import com.demo.kafka.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("/kafka/produce")
    public String produce(@RequestBody String message) {
        // Send message with callback function.
        kafkaTemplate.send(Constants.TOPIC, message).addCallback(
            new SuccessCallback<SendResult<String, Object>>() {
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info("Send success. offset: {}", result.getRecordMetadata().offset());
            }
        }, new FailureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                log.info("Send failed. error: {}", throwable.getMessage());
            }
        });
        return "OK";
    }
}
