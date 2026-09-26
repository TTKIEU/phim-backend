package com.phim.phim_backend.post;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        @Valid @RequestBody CreatePostRequest request
    ){
        //purely for testing; replace when connected Firebase
        String temporaryUserId = "test-user";

        //hand off to service
        return postService.createPost(
            temporaryUserId,
            request
        );
    }
    
}
