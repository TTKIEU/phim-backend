package com.phim.phim_backend.post;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phim.phim_backend.idempotency.IdempotencyRecord;
import com.phim.phim_backend.idempotency.IdempotencyRepository;


@Service
public class PostService {
    private final PostRepository postRepository;
    private final IdempotencyRepository idempotencyRepository;

    public PostService(PostRepository postRepository, IdempotencyRepository idempotencyRepository){
        this.postRepository = postRepository;
        this.idempotencyRepository = idempotencyRepository;
    }

    @Transactional 
    public Post createPost(
        String userId,
        String idempotencyKey,
        CreatePostRequest request
    ){
        //Look up in our idempotency record
        //if it exists, don't make a duplicate
        var existing = idempotencyRepository.findByUserIdAndIdempotencyKey(userId, idempotencyKey);
        if (existing.isPresent()){
            return postRepository.findById(existing.get().getPostId()).orElseThrow();
        }
        Post post = new Post(
            userId,
            request.movieId(),
            request.rating(),
            request.notes()
        );
        Post savedPost = postRepository.save(post);

        IdempotencyRecord record = new IdempotencyRecord(
            userId, 
            idempotencyKey, 
            savedPost.getId());
        idempotencyRepository.save(record);
        return savedPost;
    }
    public Post getPost(UUID id){
        return postRepository.findById(id).orElseThrow(()->new RuntimeException("Post not found"));
    }
}
