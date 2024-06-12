package com.example.proxysql.ha.global.service;


import com.example.proxysql.ha.global.dto.PostDto;

public interface MessageQueueService {

    void send(PostDto postDto);
}
