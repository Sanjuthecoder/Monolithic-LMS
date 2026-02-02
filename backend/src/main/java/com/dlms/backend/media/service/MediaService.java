package com.dlms.backend.media.service;

import com.dlms.backend.media.model.MediaMetadata;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface MediaService {
    MediaMetadata uploadFile(MultipartFile file) throws IOException;

    String getAccessUrl(String mediaId);

    void deleteMedia(String mediaId);

    void assignCourse(String mediaId, String courseId);
}
