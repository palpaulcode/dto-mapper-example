package dev.paul.dtoexample.repository;

import dev.paul.dtoexample.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
