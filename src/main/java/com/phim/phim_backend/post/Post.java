package com.phim.phim_backend.post;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//Spring boot indicator
//map this java object to a database table called posts
@Entity 
@Table (name="posts")
public class Post {

    //define all the features of a post
    @Id 
    @GeneratedValue (strategy=GenerationType.UUID)
    private UUID id;

    @Column (nullable=false)
    private String userId;

    @Column (nullable = false)
    private long movieId;

    @Column (nullable = false)
    private double rating;

    private String notes;

    @Column(nullable = false)
    private Instant createdAt;
    
    protected Post(){}

    public Post(
        String userId,
        long movieId,
        double rating,
        String notes
        ) {
            this.userId = userId;
            this.movieId = movieId;
            this.rating = rating;
            this.notes = notes;
            this.createdAt = Instant.now();
        }

    public UUID getId(){
        return id;
    }

    public String getUserId(){
        return userId;
    }

    public long getMovieId(){
        return movieId;
    }

    public double getRating(){
        return rating;
    }

    public String getNotes(){
        return notes;
    }

    public Instant getCreatedAt(){
        return createdAt;
    }

}
