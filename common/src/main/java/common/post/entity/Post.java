package common.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
    private Long userId;

    @Column(nullable = false)
    private String contents;

    public Post(Long userId, String contents) {
        this.userId = userId;
        this.contents = contents;
    }
}
