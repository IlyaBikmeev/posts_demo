package ru.top.posts_demo.service;

import ru.top.posts_demo.entity.Post;
import ru.top.posts_demo.entity.dto.request.PostRequest;
import ru.top.posts_demo.entity.dto.response.PostResponse;

import java.util.List;
import java.util.UUID;

public interface PostService {
    PostResponse findById(UUID id);
    PostResponse save(PostRequest dto);

    List<PostResponse> allUserPosts(UUID userId);

    void delete(UUID postId);

    PostResponse update(UUID postId, PostRequest dto);
}
