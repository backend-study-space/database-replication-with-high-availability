package com.example.proxysql.ha.service;

import com.example.proxysql.ha.global.service.KafkaConsumerService;
import com.example.proxysql.ha.repository.JdbcRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
@Slf4j
public class PostScheduledTasks {

    private final KafkaConsumerService kafkaConsumerService;

    private final JdbcRepository jdbcRepository;

    @Scheduled(fixedRate = 1000)
    @Transactional
    protected void scheduledTask() {
        jdbcRepository.insertBulk(
                kafkaConsumerService
                        .drainPayloads()
                        .stream()
                        .toList()
        );

        log.info("{} 건 삽입 완료", kafkaConsumerService.drainPayloads().size());
    }
}
