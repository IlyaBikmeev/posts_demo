package ru.top.posts_demo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.top.posts_demo.entity.Post;
import ru.top.posts_demo.entity.User;
import ru.top.posts_demo.entity.dto.request.PostRequest;
import ru.top.posts_demo.entity.dto.response.PostResponse;
import ru.top.posts_demo.mapper.PostMapper;
import ru.top.posts_demo.repository.PostRepository;
import ru.top.posts_demo.repository.UserRepository;
import ru.top.posts_demo.service.PostService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    @Override
    public PostResponse findById(UUID id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Post with id %s doesn't exist!", id)
                ));
        return postMapper.postToPostResponse(post);
    }

    @Override
    public PostResponse save(PostRequest dto) {
        User user = userRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("User with id %s doesn't exist!", dto.getAuthorId())
                ));
        Post post = postMapper.postRequestToPost(dto);
        post.setAuthor(user);
        post.setPublicationDate(LocalDateTime.now());
        post.setId(UUID.randomUUID());

        postRepository.save(post);
        log.info(String.format("Post %s has been successfully created and saved!", post.getId()));
        return postMapper.postToPostResponse(post);
    }

    @Override
    public List<PostResponse> allUserPosts(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("User with id %s doesn't exist!", userId)
                ));
        return postMapper.postsToPostResponses(user.getPosts());
    }

    @Override
    public void delete(UUID postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Post with id %s doesn't exist!", postId)
                ));
        postRepository.delete(post);
        log.info(String.format("Post with id %s has been successfully deleted!", postId));
    }

    @Override
    public PostResponse update(UUID postId, PostRequest dto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Post with id %s doesn't exist!", postId)
                ));

        post.setText(dto.getText());
        post.setTitle(dto.getTitle());
        return postMapper.postToPostResponse(
                postRepository.save(post)
        );
    }

    @ExceptionHandler(value = RuntimeException.class)
    public String exceptionHandler(Exception ex) {
        return ex.getMessage();
    }
}
