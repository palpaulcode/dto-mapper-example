package dev.paul.dtoexample.service;

import dev.paul.dtoexample.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();

    Post getPostById(Long id);

    Post createPost(Post post);

    Post updatePost(Long id, Post post);

    void deletePost(Long id);
}
