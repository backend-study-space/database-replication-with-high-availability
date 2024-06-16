package com.example.proxysql.ha.global.service;

import com.example.proxysql.ha.service.PostService;
import com.fasterxml.jackson.core.type.TypeReference;
import common.post.dto.PostDto;
import common.post.entity.Post;
import common.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.backoff.BackOff;

import javax.sql.DataSource;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.example.proxysql.ha.util.JsonUtils.convertObjectToDto;

@Component
@RequiredArgsConstructor
@Getter
@Slf4j
@Transactional
public class KafkaConsumerService {

    private final PostRepository postRepository;

    private final PostService postService;

    private final Queue<PostDto> payloads = new ConcurrentLinkedQueue<>();
    @KafkaListener(topics = "POST_INSERT", groupId = "my-group")
    public void insertTopicConsume(ConsumerRecord<String, Object> consumerRecord) {
        PostDto postDto = convertObjectToDto(consumerRecord.value(), PostDto.class);

        payloads.add(postDto);

        postService.insert(payloads);
    }

    @KafkaListener(topics = "POST_DELETE", groupId = "my-group")
    public void deleteTopicConsume(ConsumerRecord<String, Object> consumerRecord) {
        List<Post> postList =
                postRepository.findAllById(
                        convertObjectToDto(consumerRecord.value(), new TypeReference<>() {})
                );

        postRepository.deleteAll(postList);
    }

}
