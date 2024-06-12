package com.example.proxysql.ha.config.service;

import com.example.proxysql.ha.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService implements MessageQueueService {

    private final String TOPIC = "my_topic";

    private final KafkaTemplate<String, PostDto> kafkaTemplate;

    @Override
    public void send(PostDto postDto) {
        kafkaTemplate.send(TOPIC, postDto);
    }
}
