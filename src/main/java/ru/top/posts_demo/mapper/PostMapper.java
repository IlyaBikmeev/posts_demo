package ru.top.posts_demo.mapper;

import org.mapstruct.Mapper;
import ru.top.posts_demo.entity.Post;
import ru.top.posts_demo.entity.dto.request.PostRequest;

@Mapper
public interface PostMapper {

    Post postRequestToPost(PostRequest postRequest);
}
