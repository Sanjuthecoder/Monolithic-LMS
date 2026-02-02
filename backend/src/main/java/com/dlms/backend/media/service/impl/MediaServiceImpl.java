package com.dlms.backend.media.service.impl;

import com.dlms.backend.media.model.MediaMetadata;
import com.dlms.backend.media.repository.MediaRepository;
import com.dlms.backend.media.service.MediaService;
import com.dlms.backend.media.service.StorageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository repository;

    @Autowired
    private StorageProvider storage;

    @Override
    public MediaMetadata uploadFile(MultipartFile file) throws IOException {
        String cid = storage.uploadFile(file);

        MediaMetadata meta = new MediaMetadata();
        meta.setFileName(file.getOriginalFilename());
        meta.setContentType(file.getContentType());
        meta.setContentIdentifier(cid);
        meta.setStorageProvider("IPFS");
        meta.setSize(file.getSize());

        return repository.save(meta);
    }

    @Override
    public String getAccessUrl(String mediaId) {
        MediaMetadata meta = repository.findById(mediaId)
                .orElseThrow(() -> new RuntimeException("Media not found"));
        return storage.getAccessUrl(meta.getContentIdentifier());
    }

    @Override
    public void deleteMedia(String mediaId) {
        if (repository.existsById(mediaId)) {
            repository.deleteById(mediaId);
        } else {
            throw new RuntimeException("Media not found with id: " + mediaId);
        }
    }

    @Override
    public void assignCourse(String mediaId, String courseId) {
        MediaMetadata media = repository.findById(mediaId)
                .orElseThrow(() -> new RuntimeException("Media not found"));
        media.setCourseId(courseId);
        repository.save(media);
    }
}
