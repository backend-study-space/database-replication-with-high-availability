package common.post.entity;

import common.domain.BaseDateTimeEntity;
import common.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Post extends BaseDateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String contents;

    private String image;

    private Post(User user, String contents, String image) {
        this.user = user;
        this.contents = contents;
        this.image = image;
    }

    public static Post create(User user, String contents, String image) {
        Objects.requireNonNull(user);
        Strings.isNotBlank(contents);

        return new Post(user, contents, image);
    }
}
