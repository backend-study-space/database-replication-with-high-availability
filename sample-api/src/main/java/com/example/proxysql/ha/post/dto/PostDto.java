package com.example.proxysql.ha.post.dto;

public record PostDto(
        Long userId,
        String contents,
        String image
) {

}
