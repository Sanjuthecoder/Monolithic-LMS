package com.dlms.backend.media.repository;

import com.dlms.backend.media.model.MediaMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends MongoRepository<MediaMetadata, String> {
    // Custom query methods can be defined here if needed
}
