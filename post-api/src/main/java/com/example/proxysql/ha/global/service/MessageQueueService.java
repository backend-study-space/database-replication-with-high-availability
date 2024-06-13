package com.example.proxysql.ha.global.service;

import core.dto.PostDto;

public interface MessageQueueService {

    void send(PostDto postDto);
}
