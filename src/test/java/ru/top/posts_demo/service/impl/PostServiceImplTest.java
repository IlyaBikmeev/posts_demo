package ru.top.posts_demo.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.top.posts_demo.entity.Post;
import ru.top.posts_demo.entity.User;
import ru.top.posts_demo.entity.dto.request.PostRequest;
import ru.top.posts_demo.entity.dto.response.PostResponse;
import ru.top.posts_demo.mapper.PostMapper;
import ru.top.posts_demo.repository.PostRepository;
import ru.top.posts_demo.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {
    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostMapper postMapper;

    @InjectMocks
    private PostServiceImpl postService;

    private Post post;
    private PostResponse postResponse;
    private PostRequest postRequest;
    private User author;

    @BeforeEach
    void init() {
        author = new User();
        author.setId(UUID.randomUUID());

        post = Post.builder()
                .id(UUID.randomUUID())
                .text("text")
                .author(author)
                .title("title")
                .publicationDate(LocalDateTime.now())
                .build();

        postResponse = PostResponse.builder()
                .postId(post.getId())
                .text("text")
                .title("title")
                .authorId(author.getId())
                .date(post.getPublicationDate())
                .build();

        postRequest = PostRequest.builder()
                .text("text")
                .title("title")
                .authorId(author.getId())
                .build();
    }

    @Test
    void findById_shouldBeSuccessful() {
        when(postMapper.postToPostResponse(post)).thenReturn(postResponse);
        when(postRepository.findById(any())).thenReturn(Optional.of(post));
        assertEquals(postResponse, postService.findById(post.getId()));
    }

    @Test
    void findById_shouldThrowException() {
        when(postRepository.findById(any())).thenReturn(Optional.empty());
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            postService.findById(post.getId());
        });

        String expectedMessage = String.format("Post with id %s doesn't exist!", post.getId());
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void save_shouldBeSuccessful() {
        when(userRepository.findById(any())).thenReturn(Optional.of(author));
        when(postMapper.postRequestToPost(postRequest)).thenReturn(post);
        when(postMapper.postToPostResponse(post)).thenReturn(postResponse);

        assertEquals(postResponse, postService.save(postRequest));
    }

    @Test
    void save_shouldThrowException() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            postService.save(postRequest);
        });

        String expectedMessage = String.format("User with id %s doesn't exist!", author.getId());
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void allUserPosts_shouldBeSuccessful() {
        when(userRepository.findById(any())).thenReturn(Optional.of(author));
        when(postMapper.postsToPostResponses(any())).thenReturn(Collections.singletonList(postResponse));

        List<PostResponse> expected = Collections.singletonList(postResponse);

        assertEquals(expected, postService.allUserPosts(author.getId()));
    }

    @Test
    void allUserPosts_shouldThrowException() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            postService.allUserPosts(author.getId());
        });

        String expectedMessage = String.format("User with id %s doesn't exist!", author.getId());
        assertEquals(expectedMessage, exception.getMessage());
    }

}