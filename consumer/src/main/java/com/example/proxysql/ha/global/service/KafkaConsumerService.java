package com.example.proxysql.ha.global.service;

import com.example.proxysql.ha.dto.PostDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
@RequiredArgsConstructor
@Getter
public class KafkaConsumerService {

    private final Queue<PostDto> payloads = new ConcurrentLinkedQueue<>();

    @KafkaListener(topics = "my_topic", groupId = "my-group")
    public void consume(ConsumerRecord<String, PostDto> consumerRecord) {
        payloads.add(consumerRecord.value());
    }

    public Queue<PostDto> drainPayloads() {
        Queue<PostDto> drainedPayloads = new ConcurrentLinkedQueue<>();

        PostDto payload;
        while ((payload = payloads.poll()) != null) {
            drainedPayloads.add(payload);
        }
        return drainedPayloads;
    }
}
