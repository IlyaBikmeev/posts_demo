package ru.top.posts_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.posts_demo.entity.Comment;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
