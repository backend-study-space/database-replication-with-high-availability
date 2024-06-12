package com.example.proxysql.ha.global.dto;

public record PostDto(
        Long userId,
        String contents,
        String image
) {

}