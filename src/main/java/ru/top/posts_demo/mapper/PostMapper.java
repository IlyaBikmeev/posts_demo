package ru.top.posts_demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.top.posts_demo.entity.Post;
import ru.top.posts_demo.entity.dto.request.PostRequest;
import ru.top.posts_demo.entity.dto.response.PostResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post postRequestToPost(PostRequest postRequest);

    @Mapping(source = "id", target = "postId")
    @Mapping(source = "publicationDate", target = "date")
    @Mapping(source = "author.id", target = "authorId")
    PostResponse postToPostResponse(Post post);

    List<PostResponse> postsToPostResponses(List<Post> posts);
}
