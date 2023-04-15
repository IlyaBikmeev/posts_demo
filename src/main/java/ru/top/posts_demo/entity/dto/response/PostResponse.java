package ru.top.posts_demo.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private UUID postId;
    private String title;
    private String text;
    private UUID authorId;
    private LocalDateTime date;
}
