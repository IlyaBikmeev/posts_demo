package ru.top.posts_demo.service;

import ru.top.posts_demo.entity.dto.request.CommentRequest;
import ru.top.posts_demo.entity.dto.response.CommentResponse;

import java.util.UUID;

public interface CommentService {
    CommentResponse save(UUID postId, CommentRequest dto);
}
