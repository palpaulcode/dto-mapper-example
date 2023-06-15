package dev.paul.dtoexample.controller;

import dev.paul.dtoexample.model.Post;
import dev.paul.dtoexample.payload.PostDto;
import dev.paul.dtoexample.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private ModelMapper modelMapper;
    private PostService postService;

    public PostController(ModelMapper modelMapper, PostService postService) {
        this.modelMapper = modelMapper;
        this.postService = postService;
    }

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts()
                .stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long id) {
        Post post = postService.getPostById(id);

        // convert entity to DTO
        PostDto postResponse = modelMapper.map(post, PostDto.class);

        return ResponseEntity.ok().body(postResponse);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        // convert DTO to entity
        Post postRequest = modelMapper.map(postDto, Post.class);

        Post post = postService.createPost(postRequest);

        // convert entity to DTO
        PostDto postResponse = modelMapper.map(post, PostDto.class);

        return new ResponseEntity<PostDto>(postResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody PostDto postDto) {
        // Convert DTO to Entity
        Post postRequest = modelMapper.map(postDto, Post.class);

        Post post = postService.updatePost(id, postRequest);

        //  entity to DTO
        PostDto postResponse = modelMapper.map(post, PostDto.class);

        return ResponseEntity.ok().body(postResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "id") Long id) {
        postService.deletePost(id);
        return new ResponseEntity<Object>("Deleted Successfully", HttpStatus.OK);
    }
}
