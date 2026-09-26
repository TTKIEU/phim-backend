package com.phim.phim_backend.post;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Transactional 
    public Post createPost(
        String userId,
        CreatePostRequest request
    ){
        Post post = new Post(
            userId,
            request.movieId(),
            request.rating(),
            request.notes()
        );
        return postRepository.save(post);
    }
}
