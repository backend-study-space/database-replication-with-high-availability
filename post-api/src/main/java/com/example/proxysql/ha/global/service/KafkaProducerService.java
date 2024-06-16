package com.example.proxysql.ha.global.service;

import com.example.proxysql.ha.global.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService<T> implements MessageQueueService<T> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    @Override
    public void send(Message<T> message) {
        kafkaTemplate.send(message.topic().name(), message.object());
    }
}
