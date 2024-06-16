package com.example.proxysql.ha.global.service;


import com.example.proxysql.ha.global.Message;

public interface MessageQueueService<T> {

    void send(Message<T> message);
}
