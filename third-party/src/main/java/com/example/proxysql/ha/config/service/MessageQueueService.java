package com.example.proxysql.ha.config.service;

import com.example.proxysql.ha.dto.PostDto;

public interface MessageQueueService {

    void send(PostDto postDto);
}
