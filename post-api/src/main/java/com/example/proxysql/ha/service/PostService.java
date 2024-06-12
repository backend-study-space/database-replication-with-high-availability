package com.example.proxysql.ha.service;

import com.example.proxysql.ha.global.dto.PostDto;
import com.example.proxysql.ha.global.service.MessageQueueService;
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
