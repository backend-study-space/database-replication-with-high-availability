package com.example.proxysql.ha.service;

import common.post.dto.PostDto;
import common.post.repository.JdbcRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final JdbcRepository jdbcRepository;

    private final DataSource dataSource;

    public void insert(Queue<PostDto> payloads) {
        try {
            if (!DataSourceUtils.getConnection(dataSource).isClosed()) {
                List<PostDto> batch = new ArrayList<>();
                PostDto post;
                while ((post = payloads.poll()) != null) {
                    batch.add(post);
                }
                if (!batch.isEmpty()) {
                    jdbcRepository.insertBulk(batch);
                }
            }
        } catch (SQLException e) {
            log.info("SQL EXCEPTION : {}", e.getMessage());
        }
    }
}
