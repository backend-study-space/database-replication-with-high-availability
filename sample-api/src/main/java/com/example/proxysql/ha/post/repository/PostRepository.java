package com.example.proxysql.ha.post.repository;

import com.example.proxysql.ha.post.dto.PostDto;

import java.util.List;

public interface PostRepository {
    void bulkInsert(List<PostDto> postList);
}
