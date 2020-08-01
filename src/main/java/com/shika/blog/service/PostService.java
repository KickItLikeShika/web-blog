package com.shika.blog.service;

import com.shika.blog.dto.PostDto;
import com.shika.blog.exception.PostNotFoundException;
import com.shika.blog.model.Post;
import com.shika.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {

    private AuthService authService;
    private PostRepository postRepository;

    @Autowired
    public PostService(AuthService authService,
                       PostRepository postRepository) {
        this.authService = authService;
        this.postRepository = postRepository;
    }

    public void createPost(PostDto postDto) {
        Post post = mapFromDtoToPost(postDto);
        postRepository.save(post);
    }

    public List<PostDto> showAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }

    public PostDto getSinglePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new PostNotFoundException("Post not found for this id: " + id));
        return mapFromPostToDto(post);
    }

    private PostDto mapFromPostToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUsername(post.getUsername());
        return postDto;
    }

    private Post mapFromDtoToPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        User currUser = authService.getCurrUser().orElseThrow(()->
                new IllegalArgumentException("No User Logged in"));
        post.setUsername(currUser.getUsername());
        post.setCreatedOn(Instant.now());
        post.setUpdatedOn(Instant.now());
        return post;
    }
}
