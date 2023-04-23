package ru.top.posts_demo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.top.posts_demo.entity.Comment;
import ru.top.posts_demo.entity.Post;
import ru.top.posts_demo.entity.User;
import ru.top.posts_demo.entity.dto.request.CommentRequest;
import ru.top.posts_demo.entity.dto.response.CommentResponse;
import ru.top.posts_demo.mapper.CommentMapper;
import ru.top.posts_demo.repository.CommentRepository;
import ru.top.posts_demo.repository.PostRepository;
import ru.top.posts_demo.repository.UserRepository;
import ru.top.posts_demo.service.CommentService;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;
    @Override
    public CommentResponse save(UUID postId, CommentRequest dto) {
        User user = userRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("User with id %s doesn't exist!", dto.getAuthorId())
                ));
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new NoSuchElementException(
                        String.format("Post with id %s doesn't exist!", postId)
                ));
        Comment comment = commentMapper.CommentRequestToComment(dto);
        comment.setAuthor(user);
        comment.setPost(post);
        comment.setId(UUID.randomUUID());

        commentRepository.save(comment);
        log.info(String.format("Post %s has been successfully created and saved!", comment.getId()));
        return commentMapper.commentToCommentResponse(comment);
    }
}
