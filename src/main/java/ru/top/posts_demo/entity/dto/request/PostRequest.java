package ru.top.posts_demo.entity.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class PostRequest {
    private String title;
    private String text;
    private UUID authorId;
}
