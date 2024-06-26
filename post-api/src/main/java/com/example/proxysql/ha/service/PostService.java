package com.example.proxysql.ha.service;

import com.example.proxysql.ha.global.Message;
import com.example.proxysql.ha.global.service.MessageQueueService;
import com.example.proxysql.ha.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.proxysql.ha.global.Message.CudType.*;
import static com.example.proxysql.ha.global.Message.Topic.*;

@Service
@RequiredArgsConstructor
public class PostService {

    private final MessageQueueService<PostDto> messageQueueService;

    public void send(PostDto message) {
        messageQueueService.send(new Message<>(INSERT, message, POST_INSERT));
    }
}
