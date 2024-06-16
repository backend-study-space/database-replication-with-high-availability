package common.post.repository;

import common.post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public void insertBulk(List<PostDto> postDtoList) {
        jdbcTemplate.batchUpdate(
                "INSERT INTO post (user_id, contents) VALUES (?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        PostDto insertPost = postDtoList.get(i);
                        ps.setLong(1, insertPost.userId());
                        ps.setString(2, insertPost.contents());
                    }

                    @Override
                    public int getBatchSize() {
                        return postDtoList.size();
                    }
                }
        );
    }
}
