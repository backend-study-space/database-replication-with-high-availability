package com.example.proxysql.ha.post.repository;

import com.example.proxysql.ha.post.dto.PostDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcPostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void bulkInsert(List<PostDto> postList) {
        jdbcTemplate.batchUpdate(
                "INSERT INTO post (contents, image, user_id) VALUES(?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        PostDto insertPost = postList.get(i);
                        ps.setString(1, insertPost.contents());
                        ps.setString(2, insertPost.image());
                        ps.setLong(3, insertPost.userId());
                    }

                    @Override
                    public int getBatchSize() {
                        return postList.size();
                    }
                }
        );

    }
}
