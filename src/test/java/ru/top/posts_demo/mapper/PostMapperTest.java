package ru.top.posts_demo.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.top.posts_demo.entity.Post;
import ru.top.posts_demo.entity.User;
import ru.top.posts_demo.entity.dto.request.PostRequest;


import static org.junit.jupiter.api.Assertions.*;

class PostMapperTest {
    @Test
    void postRequestToPost_shouldBeSuccessful() {
        PostRequest postRequest = PostRequest
                .builder()
                .text("text")
                .title("title")
                .build();

        Post expected = Post
                .builder()
                .title("title")
                .text("text")
                .build();

        PostMapper mapper = Mappers.getMapper(PostMapper.class);
        Post actual = mapper.postRequestToPost(postRequest);

        assertEquals(actual.getTitle(), expected.getTitle());
        assertEquals(actual.getText(), expected.getText());
    }
}