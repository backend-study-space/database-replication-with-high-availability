package com.example.proxysql.ha.api;

import com.example.proxysql.ha.service.DeleteService;
import com.example.proxysql.ha.service.PostService;
import com.example.proxysql.ha.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    private final DeleteService deleteService;

    @PostMapping
    public void post(@RequestBody PostDto postDto) {
        postService.send(postDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteService.delete(id);
    }

    @DeleteMapping
    public void bulkDelete(@RequestBody List<Long> ids) {
        deleteService.bulkDelete(ids);
    }
}
