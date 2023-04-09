package ru.top.posts_demo.entity.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class PostResponse {
    private UUID postId;
    private String title;
    private String text;
    private UUID authorId;
    private LocalDateTime date;
}
