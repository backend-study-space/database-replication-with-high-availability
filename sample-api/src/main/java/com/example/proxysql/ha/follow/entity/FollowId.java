package com.example.proxysql.ha.follow.entity;

import com.example.proxysql.ha.user.entity.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static lombok.AccessLevel.PROTECTED;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode
public class FollowId implements Serializable {

    @ManyToOne
    private User follower;

    @ManyToOne
    private User followee;

}
