package com.phim.phim_backend.post;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;


//ENDPOINT
@RestController
@RequestMapping("/api/posts")
public class PostController {

    //handles logic (creating and saving post to repository)
    public final PostService postService;

    //Dependency Injection
    public PostController(PostService postService){
        this.postService = postService;
    }

    //return created status
    //Maps HTTP Post to this function
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(
        //CreatePostRequest = all required information for a post
        @RequestHeader("Idempotency-Key")
        String idempotencyKey,

        @Valid 
        @RequestBody 
        CreatePostRequest request
    ){
        //purely for testing; replace when connected Firebase
        String temporaryUserId = "test-user";

        //hand off to service
        return postService.createPost(
            temporaryUserId,
            idempotencyKey,
            request
        );
    }
    @GetMapping("/{id}")
    public Post getPost(@PathVariable UUID id) {
        return postService.getPost(id);
    }
    

}
