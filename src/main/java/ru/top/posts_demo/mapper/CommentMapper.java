package ru.top.posts_demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.top.posts_demo.entity.Comment;
import ru.top.posts_demo.entity.dto.request.CommentRequest;
import ru.top.posts_demo.entity.dto.response.CommentResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment CommentRequestToComment(CommentRequest commentRequest);

    @Mapping(source = "id", target = "commentId")
    @Mapping(source = "post.id", target = "postId")
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "post.publicationDate", target = "date")

    CommentResponse commentToCommentResponse(Comment comment);

    List<CommentResponse> commentsToCommentResponses(List<Comment> comments);
}
