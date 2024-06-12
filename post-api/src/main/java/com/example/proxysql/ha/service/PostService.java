package com.example.proxysql.ha.service;

import com.example.proxysql.ha.dto.PostDto;
import com.example.proxysql.ha.config.service.MessageQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final MessageQueueService messageQueueService;

    public void send(PostDto postDto) {
        messageQueueService.send(postDto);
    }
}
