package com.phim.phim_backend.post;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

//defines JSON package payload
public record CreatePostRequest (
    @NotNull 
    Long movieId,

    @Min(0)
    @Max(10)
    double rating,

    String notes
){}
