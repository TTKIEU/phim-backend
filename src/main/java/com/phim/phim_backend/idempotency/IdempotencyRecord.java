package com.phim.phim_backend.idempotency;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity 
@Table (
    name = "idempotency_records",
    uniqueConstraints = {
        @UniqueConstraint (
            columnNames={"user_id","idempotency_key"})})
public class IdempotencyRecord {
    @Id 
    @GeneratedValue (strategy=GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable=false)
    private String userId;

    @Column (name="idempotency_key",nullable = false)
    private String idempotencyKey;

    @Column (name = "post_id",nullable=false)
    private UUID postId;

    @Column(nullable=false)
    private Instant createdAt;

    @Column (name="request_hash", nullable = false)
    private String requestHash;

    protected IdempotencyRecord(){};

    public IdempotencyRecord(
        String userId,
        String idempotencyKey,
        UUID postId,
        String requestHash
    ){
        this.userId=userId;
        this.idempotencyKey=idempotencyKey;
        this.postId=postId;
        this.requestHash=requestHash;
        this.createdAt=Instant.now();
    }
    public UUID getPostId(){
        return postId;
    }
    public String getRequestHash(){
        return requestHash;
    }
    
    
}
