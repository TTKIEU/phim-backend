package com.phim.phim_backend.post;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phim.phim_backend.exception.IdempotencyConflictException;
import com.phim.phim_backend.exception.PostNotFoundException;
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

    private String hashRequest(CreatePostRequest request){
        try {
            String requestString =
                request.movieId() + "|" +
                request.rating() + "|" +
                request.notes();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(requestString.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Unable to hash request", e);
        }
    }

    @Transactional 
    public Post createPost(
        String userId,
        String idempotencyKey,
        CreatePostRequest request
    ){
        String requestHash = hashRequest(request);
        //Look up in our idempotency record
        //if it exists, don't make a duplicate
        var existing = idempotencyRepository.findByUserIdAndIdempotencyKey(userId, idempotencyKey);
        if (existing.isPresent()){
            IdempotencyRecord record = existing.get();
            if (!record.getRequestHash().equals(requestHash)) {
                    throw new IdempotencyConflictException();
                }
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
            savedPost.getId(),
            requestHash);
        idempotencyRepository.save(record);
        return savedPost;
    }
    public Post getPost(UUID id){
        
        return postRepository.findById(id).orElseThrow(()->new PostNotFoundException(id));
    }
    
}
