package com.example.proxysql.ha.service;

import com.example.proxysql.ha.global.Message;
import com.example.proxysql.ha.global.service.MessageQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.proxysql.ha.global.Message.CudType.*;
import static com.example.proxysql.ha.global.Message.Topic.*;

@Service
@RequiredArgsConstructor
public class DeleteService {

    private final MessageQueueService<List<Long>> messageQueueService;

    public void bulkDelete(List<Long> ids) {
        messageQueueService.send(new Message<>(DELETE, ids, POST_DELETE));
    }

    public void delete(Long id) {
        messageQueueService.send(new Message<>(DELETE, List.of(id), POST_DELETE));
    }
}
