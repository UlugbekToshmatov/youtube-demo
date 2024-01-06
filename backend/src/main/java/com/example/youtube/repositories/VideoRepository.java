package com.example.youtube.repositories;

import com.example.youtube.entities.Video;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends MongoRepository<Video, String> {
    Optional<Video> findByIdAndDeletedFalse(String id);
}
