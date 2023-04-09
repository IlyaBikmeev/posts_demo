package ru.top.posts_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.posts_demo.entity.Post;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
