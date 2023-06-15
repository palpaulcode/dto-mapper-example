package dev.paul.dtoexample.service.impl;

import dev.paul.dtoexample.model.Post;
import dev.paul.dtoexample.repository.PostRepository;
import dev.paul.dtoexample.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        super();
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        Optional<Post> result = postRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Post with id" + id + " not found");
            // return null;
        }
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long id, Post postRequest) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post with id" + id + " not found"));

        post.setTitle(postRequest.getTitle());
        post.setDescription(postRequest.getDescription());
        post.setContent(postRequest.getContent());

        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post with id" + id + " not found"));

        postRepository.delete(post);
    }
}
