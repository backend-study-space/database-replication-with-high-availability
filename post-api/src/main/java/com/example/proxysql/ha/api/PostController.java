package com.example.proxysql.ha.api;

import com.example.proxysql.ha.service.PostService;
import core.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public void post(@RequestBody PostDto postDto) {
        postService.send(postDto);
    }
}
