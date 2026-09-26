package com.phim.phim_backend.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

//Spring will generate implementations for save, findbyid,
//findAll, and delete
//Post = entity this repository manages
//UUID = the type of Post's primary key
public interface PostRepository extends JpaRepository<Post, UUID> {  
}
