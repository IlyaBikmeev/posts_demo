package ru.top.posts_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @Column(name ="id")
    private UUID id;

    @Column(name = "nickname")
    private String nickname;

    @OneToMany(mappedBy = "author")
    private List<Post> posts;
}
