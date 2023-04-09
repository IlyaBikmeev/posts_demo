package ru.top.posts_demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.top.posts_demo.entity.Post;
import ru.top.posts_demo.entity.User;
import ru.top.posts_demo.entity.dto.request.PostRequest;
import ru.top.posts_demo.entity.dto.response.PostResponse;
import ru.top.posts_demo.repository.PostRepository;
import ru.top.posts_demo.repository.UserRepository;
import ru.top.posts_demo.service.PostService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    @Override
    public PostResponse findById(UUID id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Post with id %s doesn't exist!", id)
                ));
        return PostResponse.builder()
                .postId(post.getId())
                .authorId(post.getAuthor().getId())
                .date(post.getPublicationDate())
                .text(post.getText())
                .title(post.getTitle())
                .build();
    }

    @Override
    public PostResponse save(PostRequest dto) {
        User user = userRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("User with id %s doesn't exist!", dto.getAuthorId())
                ));

        Post post = Post.builder()
                .id(UUID.randomUUID())
                .publicationDate(LocalDateTime.now())
                .title(dto.getTitle())
                .text(dto.getText())
                .author(user)
                .build();

        postRepository.save(post);

        return PostResponse.builder()
                .postId(post.getId())
                .authorId(dto.getAuthorId())
                .date(post.getPublicationDate())
                .text(post.getText())
                .title(post.getTitle())
                .build();
    }

    @Override
    public List<PostResponse> allUserPosts(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("User with id %s doesn't exist!", userId)
                ));
        return user.getPosts()
                .stream()
                .map(post ->
                    PostResponse.builder()
                            .postId(post.getId())
                            .authorId(post.getAuthor().getId())
                            .date(post.getPublicationDate())
                            .text(post.getText())
                            .title(post.getTitle())
                            .build()
                ).collect(Collectors.toList());
    }
}
